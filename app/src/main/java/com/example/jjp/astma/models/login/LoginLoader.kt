package com.example.jjp.astma.models.login

import com.example.jjp.astma.api.ApiService
import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.api.response.SignInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginLoader
@Inject constructor(private val api: ApiService) {
    fun signInUser(request: SignInRequest, callback: Callback<SignInResponse>) {
        api.signIn(request).enqueue(SignInApiCallback(callback))
    }

    private inner class SignInApiCallback internal constructor(val callback: Callback<SignInResponse>) : Callback<SignInResponse> {
        override fun onFailure(call: Call<SignInResponse>, t: Throwable?) {
            callback.onFailure(call, t)
        }

        override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>?) {
            callback.onResponse(call, response)
        }
    }
}