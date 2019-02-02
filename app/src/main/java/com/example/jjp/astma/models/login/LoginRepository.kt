package com.example.jjp.astma.models.login

import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.api.request.SignUpRequest
import com.example.jjp.astma.api.response.LoginResponse
import com.example.jjp.astma.models.listeners.ModelLoadingListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
import org.json.JSONObject

@Singleton
class LoginRepository
@Inject constructor(private val loginLoader: LoginLoader) {
    fun signInUser(signInRequest: SignInRequest, modelLoadingListener: ModelLoadingListener<String>) {
        loginLoader.signInUser(signInRequest, object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.let { modelLoadingListener.onModelLoaded(it.token) }
                        ?: modelLoadingListener.onModelError(getError(response))
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable?) {
                modelLoadingListener.onModelFailure(t)
            }
        })
    }

    fun signUpUser(signUpRequest: SignUpRequest, modelLoadingListener: ModelLoadingListener<String>) {
        loginLoader.signUpUser(signUpRequest, object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.let { modelLoadingListener.onModelLoaded(it.token) }
                        ?: modelLoadingListener.onModelError(getError(response))
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable?) {
                modelLoadingListener.onModelFailure(t)
            }
        })
    }

    fun getError(response : Response<LoginResponse>) : String{
        val jObjError = JSONObject(response.errorBody()?.string())
        return jObjError.getString(ERROR)
    }

    companion object {
        private const val ERROR = "error"
    }
}