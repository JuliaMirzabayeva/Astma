package com.example.jjp.astma.modules.login

import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.models.listeners.EmptyLoadingListener
import com.example.jjp.astma.models.listeners.ModelLoadingListener
import com.example.jjp.astma.models.login.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository) {
    fun signInUser(signInRequest: SignInRequest,
                   onResult: ((String)) -> Unit,
                   onFailure: ((Throwable?) -> Unit)? = null) {

        loginRepository.signInUser(signInRequest, object : ModelLoadingListener<String> {
            override fun onModelLoaded(model: String) {
                onResult(model)
            }

            override fun onModelFailure(error: Throwable?) {
                onFailure?.invoke(error)
            }
        })
    }
}