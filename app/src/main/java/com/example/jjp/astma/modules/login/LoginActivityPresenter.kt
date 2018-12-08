package com.example.jjp.astma.modules.login

import android.os.Bundle
import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.models.login.LoginRepository
import com.example.jjp.astma.preferences.CommonPreferencesHelper
import nucleus.presenter.Presenter
import javax.inject.Inject

class LoginActivityPresenter : Presenter<LoginActivity>() {
    @Inject lateinit var loginRepository: LoginRepository

    private var loginUseCase: LoginUseCase? = null
    private var commonPreferencesHelper : CommonPreferencesHelper? = null

    private val onResult: (token : String) -> Unit = {
        commonPreferencesHelper?.userToken = it
        view?.goToChartActivity()
    }

    private val onError: (error: String?) -> Unit = { it ->
        view?.showError(it)
    }

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        App.component().inject(this)
        loginUseCase = LoginUseCase(loginRepository)
    }

    override fun onTakeView(view: LoginActivity?) {
        super.onTakeView(view)
        commonPreferencesHelper = CommonPreferencesHelper(view!!.baseContext)
    }

    fun signInUser(email: String, password: String) {
        loginUseCase?.signInUser(SignInRequest(email, password), onResult, onError)
    }
}