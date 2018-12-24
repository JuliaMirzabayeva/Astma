package com.example.jjp.astma.models.quotes

import com.example.jjp.astma.api.request.EditQuoteRequest
import com.example.jjp.astma.api.request.QuoteRequest
import com.example.jjp.astma.api.request.QuotesRequest
import com.example.jjp.astma.api.response.QuoteResponse
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.models.listeners.ModelLoadingListener
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesRepository
@Inject constructor(private val quotesLoader: QuotesLoader) {
    private val quoteConverter = QuoteConverter()

    fun loadQuotes(quotesRequest: QuotesRequest, modelLoadingListener: ModelLoadingListener<List<Quote>>) {
        quotesLoader.loadQuotes(quotesRequest, object : Callback<List<QuoteResponse>> {
            override fun onResponse(call: Call<List<QuoteResponse>>, response: Response<List<QuoteResponse>>) {
                response.body()?.let {
                    val quotes = quoteConverter.convertQuotesResponseToQuoteList(it)
                    modelLoadingListener.onModelLoaded(quotes)
                } ?: modelLoadingListener.onModelError(getError(response))
            }

            override fun onFailure(call: Call<List<QuoteResponse>>, t: Throwable) {
                modelLoadingListener.onModelFailure(t)
            }
        })
    }

    fun addQuote(addQuoteRequest: QuoteRequest, modelLoadingListener: ModelLoadingListener<Quote>) {
        quotesLoader.addQuote(addQuoteRequest, object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                response.body()?.let {
                    val quote = quoteConverter.convertQuoteResponseToQuote(it)
                    modelLoadingListener.onModelLoaded(quote)
                } ?: modelLoadingListener.onModelError(getError(response))

            }

            override fun onFailure(call: Call<QuoteResponse>, t: Throwable?) {
                modelLoadingListener.onModelFailure(t)
            }
        })
    }

    fun editQuote(editQuoteRequest: EditQuoteRequest, modelLoadingListener: ModelLoadingListener<Quote>) {
        quotesLoader.editQuote(editQuoteRequest, object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                response.body()?.let {
                    val quote = quoteConverter.convertQuoteResponseToQuote(it)
                    modelLoadingListener.onModelLoaded(quote)
                } ?: modelLoadingListener.onModelError(getError(response))

            }

            override fun onFailure(call: Call<QuoteResponse>, t: Throwable?) {
                modelLoadingListener.onModelFailure(t)
            }
        })
    }

    private fun <T> getError(response: Response<T>): String {
        val jObjError = JSONObject(response.errorBody()?.string())
        return jObjError.getString(ERROR)
    }

    companion object {
        private const val ERROR = "error"
    }
}