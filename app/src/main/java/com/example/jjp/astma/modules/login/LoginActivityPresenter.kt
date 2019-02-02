package com.example.jjp.astma.modules.login

import android.os.Bundle
import com.example.jjp.astma.api.request.SignInRequest
import com.example.jjp.astma.api.request.SignUpRequest
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.models.login.LoginRepository
import com.example.jjp.astma.preferences.CommonPreferencesHelper
import nucleus.presenter.Presenter
import java.util.*
import javax.inject.Inject

class LoginActivityPresenter : Presenter<LoginActivity>() {
    @Inject lateinit var loginRepository: LoginRepository
    @Inject lateinit var commonPreferencesHelper: CommonPreferencesHelper

    private var loginUseCase: LoginUseCase? = null

    private val onResult: (token: String) -> Unit = {
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

    fun signInUser(email: String, password: String) {
        loginUseCase?.signInUser(SignInRequest(email, password), onResult, onError)
    }

    fun signUpUser(email: String,
                   password: String,
                   name: String,
                   surname: String,
                   birth: Date,
                   sex: Int,
                   height: Int,
                   weight: Int) {
        loginUseCase?.signUpUser(SignUpRequest(login = email,
                password = password,
                name = name,
                surname = surname,
                birthDate = birth,
                sex = sex,
                height = height,
                weight = weight), onResult, onError)
    }

    fun getCalendar(day: Int, month: Int, year: Int): GregorianCalendar {
        return GregorianCalendar(year, month, day)
    }
}