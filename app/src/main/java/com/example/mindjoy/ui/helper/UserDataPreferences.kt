package com.example.mindjoy.ui.helper

import android.content.Context
import android.content.SharedPreferences

class UserDataPreferences(context: Context) {

    companion object {
        const val PREFS_NAME = "user_data"
        const val NAME = "name"
        const val EXPRESSION = "expression"
        const val MENTAL_HEALTH = "mental_health"
    }

    private var preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveName(name: String){
        val editor = preferences.edit()
        editor.putString(NAME, name)
        editor.apply()
    }

    fun getName(): String? {
        return preferences.getString(NAME, "MindJoy User")
    }

    fun saveExpression(status: String){
        val editor = preferences.edit()
        editor.putString(EXPRESSION, status)
        editor.apply()
    }

    fun getExpression(): String? {
        return preferences.getString(EXPRESSION, "-")
    }

    fun saveMentalHealth(status: String){
        val editor = preferences.edit()
        editor.putString(MENTAL_HEALTH, status)
        editor.apply()
    }

    fun getMentalHealth(): String? {
        return preferences.getString(MENTAL_HEALTH, "-")
    }
}