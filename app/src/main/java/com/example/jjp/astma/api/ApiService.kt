package com.example.jjp.astma.api

import com.example.jjp.astma.api.request.QuoteRequest
import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.api.response.AddQuoteResponse
import com.example.jjp.astma.api.response.SignInResponse
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body

interface ApiService {
    @POST("api/login")
    fun signIn(@Body request: SignInRequest): Call<SignInResponse>

    @POST("api/chart/add")
    fun addQuote(@Body request: QuoteRequest): Call<AddQuoteResponse>
}