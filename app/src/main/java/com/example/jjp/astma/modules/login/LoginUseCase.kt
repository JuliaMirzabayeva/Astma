package com.example.jjp.astma.modules.login

import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.data.User
import com.example.jjp.astma.models.ModelLoadingListener
import com.example.jjp.astma.models.login.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository) {
    fun signInUser(signInRequest: SignInRequest,
                   onResult: (User) -> Unit,
                   onFailure: (Throwable?) -> Unit) {

        loginRepository.signInUser(signInRequest, object : ModelLoadingListener<User> {
            override fun onModelLoaded(model: User) {
                onResult(model)
            }

            override fun onModelFailure(error: Throwable?) {
                onFailure(error)
            }
        })
    }
}