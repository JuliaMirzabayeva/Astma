package com.example.jjp.astma.modules.login

import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.models.EmptyLoadingListener
import com.example.jjp.astma.models.login.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository) {
    fun signInUser(signInRequest: SignInRequest,
                   onResult: () -> Unit,
                   onFailure: ((Throwable?) -> Unit)? = null) {

        loginRepository.signInUser(signInRequest, object : EmptyLoadingListener {
            override fun onModelLoaded() {
                onResult()
            }

            override fun onModelFailure(error: Throwable?) {
                onFailure?.invoke(error)
            }
        })
    }
}