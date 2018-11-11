package com.example.jjp.astma.modules.login

import android.os.Bundle
import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.models.login.LoginRepository
import nucleus.presenter.Presenter
import javax.inject.Inject

class LoginActivityPresenter : Presenter<LoginActivity>() {
    @Inject lateinit var loginRepository: LoginRepository

    private var loginUseCase: LoginUseCase? = null

    private val onResult: () -> Unit = {
        view?.goToChartActivity()
    }

    private val onFailure: (throwable: Throwable?) -> Unit = {

    }

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        App.component().inject(this)
        loginUseCase = LoginUseCase(loginRepository)
    }

    fun signInUser(email: String, password: String) {
        loginUseCase?.signInUser(SignInRequest(email, password), onResult, onFailure)
    }
}