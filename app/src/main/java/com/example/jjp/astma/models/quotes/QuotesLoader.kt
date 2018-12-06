package com.example.jjp.astma.models.quotes

import com.example.jjp.astma.api.ApiService
import com.example.jjp.astma.api.request.QuoteRequest
import com.example.jjp.astma.api.response.AddQuoteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesLoader
@Inject constructor(private val api: ApiService) {
    fun addQuote(request: QuoteRequest, callback: Callback<AddQuoteResponse>) {
        api.addQuote(request).enqueue(AddQuoteApiCallback(callback))
    }

    private inner class AddQuoteApiCallback internal constructor(val callback: Callback<AddQuoteResponse>) : Callback<AddQuoteResponse> {
        override fun onFailure(call: Call<AddQuoteResponse>, t: Throwable?) {
            callback.onFailure(call, t)
        }

        override fun onResponse(call: Call<AddQuoteResponse>, response: Response<AddQuoteResponse>?) {
            callback.onResponse(call, response)
        }
    }
}