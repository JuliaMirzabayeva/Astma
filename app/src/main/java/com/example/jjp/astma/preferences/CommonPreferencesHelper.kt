package com.example.jjp.astma.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class CommonPreferencesHelper(context: Context) : Preferences {
    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    var userToken : String? = preferences.getString(USER_TOKEN, null)
        set(value) = preferences.edit().putString(USER_TOKEN, value).apply()

    override fun clear() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val USER_TOKEN = "user_token"
    }
}