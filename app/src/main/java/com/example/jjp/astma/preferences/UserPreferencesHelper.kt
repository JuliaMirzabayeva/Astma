package com.example.jjp.astma.preferences

import android.content.SharedPreferences

class UserPreferencesHelper
constructor(var preferences: SharedPreferences) : Preferences {
    var topBound: Float
        get() = preferences.getFloat(TOP_BOUNDS, -1f)
        set(value) = preferences.edit().putFloat(TOP_BOUNDS, value).apply()

    var bottomBound: Float
        get() = preferences.getFloat(BOTTOM_BOUNDS, -1f)
        set(value) = preferences.edit().putFloat(BOTTOM_BOUNDS, value).apply()

    var userRate: Float
        get() = preferences.getFloat(USER_RATE, -1f)
        set(value) = preferences.edit().putFloat(USER_RATE, value).apply()

    override fun clear() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val TOP_BOUNDS = "top_bound"
        private const val BOTTOM_BOUNDS = "bottom_bound"
        private const val USER_RATE = "user_rate"
    }
}