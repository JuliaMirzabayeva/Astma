package com.example.jjp.astma.models.quotes

import com.example.jjp.astma.api.request.QuoteRequest
import com.example.jjp.astma.api.response.AddQuoteResponse
import com.example.jjp.astma.data.Quote
import com.example.jjp.astma.data.QuoteDate
import com.example.jjp.astma.models.listeners.ModelLoadingListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesRepository
@Inject constructor(private val quotesLoader: QuotesLoader) {
    fun addQuote(addQuoteRequest: QuoteRequest, modelLoadingListener: ModelLoadingListener<Quote>) {
        quotesLoader.addQuote(addQuoteRequest, object : Callback<AddQuoteResponse> {
            override fun onResponse(call: Call<AddQuoteResponse>, response: Response<AddQuoteResponse>) {
                modelLoadingListener.onModelLoaded(formatQuote(response.body()!!))
            }

            override fun onFailure(call: Call<AddQuoteResponse>, t: Throwable?) {
                modelLoadingListener.onModelFailure(t)
            }
        })
    }

    fun formatQuote(quoteResponse: AddQuoteResponse): Quote {
        val calendar = Calendar.getInstance()
        calendar.time = quoteResponse.date
        val quoteDate = QuoteDate(calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR))
        return Quote(quoteResponse.value, quoteDate, quoteResponse.isMorning)
    }
}