package com.example.jjp.astma.api

import com.example.jjp.astma.api.request.*
import com.example.jjp.astma.api.response.QuoteResponse
import com.example.jjp.astma.api.response.LoginResponse
import okhttp3.ResponseBody
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body

interface ApiService {
    @POST("api/user/login")
    fun signIn(@Body request: SignInRequest): Call<LoginResponse>

    @POST("api/user/signup")
    fun signUp(@Body request: SignUpRequest): Call<LoginResponse>

    @POST("api/chart/add")
    fun addQuote(@Body request: QuoteRequest): Call<QuoteResponse>

    @POST("api/chart/edit")
    fun editQuote(@Body request: EditQuoteRequest): Call<QuoteResponse>

    @POST("api/chart")
    fun loadQuotes(@Body request : QuotesRequest): Call<List<QuoteResponse>>

    @POST("api/chart/report")
    fun getReport(@Body request : ReportRequest): Call<ResponseBody>
}