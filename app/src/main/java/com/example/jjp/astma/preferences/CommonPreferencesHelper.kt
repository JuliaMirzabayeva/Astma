package com.example.jjp.astma.preferences

import android.content.SharedPreferences


class CommonPreferencesHelper(var preferences: SharedPreferences) : Preferences {
    var userToken: String?
        get() = preferences.getString(USER_TOKEN, null)
        set(value) = preferences.edit().putString(USER_TOKEN, value).apply()

    var chartMonth: Int
        get() = preferences.getInt(CHART_MONTH, -1)
        set(value) = preferences.edit().putInt(CHART_MONTH, value).apply()

    var chartYear: Int
        get() = preferences.getInt(CHART_YEAR, -1)
        set(value) = preferences.edit().putInt(CHART_YEAR, value).apply()

    override fun clear() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val USER_TOKEN = "user_token"
        private const val CHART_MONTH = "chart_month"
        private const val CHART_YEAR = "chart_year"
    }
}