package com.example.jjp.astma.modules.login

import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.api.request.SignUpRequest
import com.example.jjp.astma.data.User
import com.example.jjp.astma.models.listeners.ModelLoadingListener
import com.example.jjp.astma.models.login.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository) {
    fun signInUser(signInRequest: SignInRequest,
                   onResult: (User) -> Unit,
                   onError: (String?) -> Unit) {

        loginRepository.signInUser(signInRequest, object : ModelLoadingListener<User> {
            override fun onModelLoaded(model: User) {
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

    fun signUpUser(signUpRequest: SignUpRequest,
                   onResult: (User) -> Unit,
                   onError: (String?) -> Unit) {

        loginRepository.signUpUser(signUpRequest, object : ModelLoadingListener<User> {
            override fun onModelLoaded(model: User) {
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