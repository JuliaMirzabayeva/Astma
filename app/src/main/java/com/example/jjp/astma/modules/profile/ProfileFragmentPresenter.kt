package com.example.jjp.astma.modules.profile

import android.os.Bundle
import com.example.jjp.astma.dagger.App
import com.example.jjp.astma.preferences.CommonPreferencesHelper
import nucleus.presenter.Presenter
import javax.inject.Inject

class ProfileFragmentPresenter : Presenter<ProfileFragment>() {
    @Inject lateinit var commonPreferencesHelper: CommonPreferencesHelper

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        App.component().inject(this)
    }

    fun clearPreferences() {
        commonPreferencesHelper.clear()
    }
}