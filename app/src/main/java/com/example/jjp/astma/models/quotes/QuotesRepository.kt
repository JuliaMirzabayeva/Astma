package com.example.jjp.astma.models.quotes

import com.example.jjp.astma.api.request.QuoteRequest
import com.example.jjp.astma.api.request.QuotesRequest
import com.example.jjp.astma.api.response.QuoteResponse
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.data.QuoteDate
import com.example.jjp.astma.models.listeners.ModelLoadingListener
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesRepository
@Inject constructor(private val quotesLoader: QuotesLoader) {
    fun loadQuotes(quotesRequest: QuotesRequest, modelLoadingListener: ModelLoadingListener<List<Quote>>){
        quotesLoader.loadQuotes(quotesRequest, object : Callback<List<QuoteResponse>>{
            override fun onResponse(call: Call<List<QuoteResponse>>, response: Response<List<QuoteResponse>>) {
                response.body()?.let { modelLoadingListener.onModelLoaded(formatQuotes(it)) }
                        ?: modelLoadingListener.onModelError(getError(response))
            }

            override fun onFailure(call: Call<List<QuoteResponse>>, t: Throwable) {
               modelLoadingListener.onModelFailure(t)
            }
        })
    }

    fun addQuote(addQuoteRequest: QuoteRequest, modelLoadingListener: ModelLoadingListener<Quote>) {
        quotesLoader.addQuote(addQuoteRequest, object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                modelLoadingListener.onModelLoaded(formatQuote(response.body()!!))
            }

            override fun onFailure(call: Call<QuoteResponse>, t: Throwable?) {
                modelLoadingListener.onModelFailure(t)
            }
        })
    }

    fun formatQuotes(quotesResponse: List<QuoteResponse>) : List<Quote>{
        return quotesResponse.map { formatQuote(it) }
    }

    fun formatQuote(quoteResponse: QuoteResponse): Quote {
        val calendar = Calendar.getInstance()
        calendar.time = quoteResponse.date
        val quoteDate = QuoteDate(calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR))
        return Quote(quoteResponse.value, quoteDate, quoteResponse.isMorning)
    }

    fun <T> getError(response : Response<T>) : String{
        val jObjError = JSONObject(response.errorBody()?.string())
        return jObjError.getString(ERROR)
    }

    companion object {
        private const val ERROR = "error"
    }
}