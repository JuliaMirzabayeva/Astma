package com.example.jjp.astma.models.quotes

import com.example.jjp.astma.api.ApiService
import com.example.jjp.astma.api.request.QuoteRequest
import com.example.jjp.astma.api.request.QuotesRequest
import com.example.jjp.astma.api.response.QuoteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesLoader
@Inject constructor(private val api: ApiService) {
    fun loadQuotes(request: QuotesRequest, callback: Callback<List<QuoteResponse>>){
        api.loadQuotes(request).enqueue(LoadQuoteApiCallback(callback))
    }

    fun addQuote(request: QuoteRequest, callback: Callback<QuoteResponse>) {
        api.addQuote(request).enqueue(AddQuoteApiCallback(callback))
    }

    private inner class LoadQuoteApiCallback internal constructor(val callback: Callback<List<QuoteResponse>>) : Callback<List<QuoteResponse>> {
        override fun onFailure(call: Call<List<QuoteResponse>>, t: Throwable) {
            callback.onFailure(call, t)
        }

        override fun onResponse(call: Call<List<QuoteResponse>>, response: Response<List<QuoteResponse>>) {
            callback.onResponse(call, response)
        }
    }

    private inner class AddQuoteApiCallback internal constructor(val callback: Callback<QuoteResponse>) : Callback<QuoteResponse> {
        override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
            callback.onFailure(call, t)
        }

        override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
            callback.onResponse(call, response)
        }
    }
}