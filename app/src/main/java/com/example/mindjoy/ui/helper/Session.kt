package com.example.mindjoy.ui.helper

import android.content.Context
import android.content.SharedPreferences

class Session(context: Context) {
    companion object {
        const val PREFS_NAME = "user_pref"
        const val LOGGED_IN = "logged_in"
    }

    private var preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveLogin(value: Boolean){
        val editor = preferences.edit()
        if (value){
            editor.putBoolean(LOGGED_IN, value)
            editor.apply()
        } else {
            editor.clear()
            editor.apply()
        }
    }

    fun getLogin(): Boolean {
        return preferences.getBoolean(LOGGED_IN, false)
    }
}