package com.example.speedotransfer.network.helpers

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object PreferenceHelper {

    private const val PREFS_NAME = "app_prefs"
    private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setOnboardingCompleted(context: Context, completed: Boolean) {
        getSharedPreferences(context).edit {
            putBoolean(KEY_ONBOARDING_COMPLETED, completed)
        }
    }

    fun isOnboardingCompleted(context: Context): Boolean {
        return getSharedPreferences(context).getBoolean(KEY_ONBOARDING_COMPLETED, false)
    }
}
