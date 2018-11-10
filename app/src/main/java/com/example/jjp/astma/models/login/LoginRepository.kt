package com.example.jjp.astma.models.login

import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.api.response.SignInResponse
import com.example.jjp.astma.data.User
import com.example.jjp.astma.models.ModelLoadingListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository
@Inject constructor(private val loginLoader: LoginLoader) {
    fun signInUser(signInRequest : SignInRequest, modelLoadingListener: ModelLoadingListener<User>) {
        loginLoader.signInUser(signInRequest, object : Callback<SignInResponse> {
            override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>) {
                modelLoadingListener.onModelLoaded(response.body()!!.user)
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable?) {
                modelLoadingListener.onModelFailure(t)
            }
        })
    }
}