package com.example.jjp.astma.models.login

import com.example.jjp.astma.api.ApiService
import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.api.request.SignUpRequest
import com.example.jjp.astma.api.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginLoader
@Inject constructor(private val api: ApiService) {
    fun signInUser(request: SignInRequest, callback: Callback<LoginResponse>) {
        api.signIn(request).enqueue(LoginApiCallback(callback))
    }

    fun signUpUser(request: SignUpRequest, callback: Callback<LoginResponse>) {
        api.signUp(request).enqueue(LoginApiCallback(callback))
    }

    private inner class LoginApiCallback internal constructor(val callback: Callback<LoginResponse>) : Callback<LoginResponse> {
        override fun onFailure(call: Call<LoginResponse>, t: Throwable?) {
            callback.onFailure(call, t)
        }

        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>?) {
            callback.onResponse(call, response)
        }
    }
}