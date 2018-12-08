package com.example.jjp.astma.modules.login

import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.models.listeners.ModelLoadingListener
import com.example.jjp.astma.models.login.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository) {
    fun signInUser(signInRequest: SignInRequest,
                   onResult: ((String)) -> Unit,
                   onError: (String?) -> Unit) {

        loginRepository.signInUser(signInRequest, object : ModelLoadingListener<String> {
            override fun onModelLoaded(model: String) {
                onResult(model)
            }

            override fun onModelError(error: String?) {
                onError(error)
            }

            override fun onModelFailure(error: Throwable?) {
                onError(null)
            }
        })
    }
}