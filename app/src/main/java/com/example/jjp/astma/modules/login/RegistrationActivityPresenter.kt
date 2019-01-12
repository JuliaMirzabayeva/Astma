package com.example.jjp.astma.modules.login

import android.os.Bundle
import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.models.login.LoginRepository
import com.example.jjp.astma.preferences.CommonPreferencesHelper
import nucleus.presenter.Presenter
import javax.inject.Inject

class RegistrationActivityPresenter : Presenter<RegistrationActivity>() {
    @Inject lateinit var loginRepository: LoginRepository
    @Inject lateinit var commonPreferencesHelper: CommonPreferencesHelper

    private var loginUseCase: LoginUseCase? = null

    private val onResult: (token : String) -> Unit = {
        commonPreferencesHelper.userToken = it
        view?.hideProgress()
        view?.goToChartActivity()
    }

    private val onError: (error: String?) -> Unit = { it ->
        view?.hideProgress()
        view?.showError(it)
    }

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        App.component().inject(this)
        loginUseCase = LoginUseCase(loginRepository)
    }

    fun signUpUser(email: String, password: String) {

    }
}