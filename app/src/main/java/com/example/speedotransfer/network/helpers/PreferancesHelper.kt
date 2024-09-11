package com.example.speedotransfer.network.helpers

import android.content.Context
import android.content.SharedPreferences

object PreferencesHelper {

    private const val PREFERENCES_NAME = "speedo_transfer_prefs"

    // Keys for your preferences
    private const val KEY_USER_TOKEN = "user_token"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    // Function to save user token
    fun saveUserToken(context: Context, token: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_USER_TOKEN, token)
        editor.apply() // or use commit() if you need synchronous saving
    }

    // Function to get user token
    fun getUserToken(context: Context): String? {
        return getPreferences(context).getString(KEY_USER_TOKEN, null)
    }

    // Function to clear all preferences
    fun clearPreferences(context: Context) {
        val editor = getPreferences(context).edit()
        editor.clear()
        editor.apply()
    }
}
