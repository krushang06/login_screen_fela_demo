package com.example.login_screen_test.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyfelaApi", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    private val userKey = "KEY_USERNAME"
    private val tokenKey = "KEY_TOKEN"

    fun setUserName(username: String) {
        editor.putString(userKey, username)
        editor.apply()
    }
    fun setToken(token: String) {
        editor.putString(tokenKey, token)
        editor.apply()
    }

    fun getUsername(): String? {
        return sharedPreferences.getString(userKey, null)
    }
    fun getSavedToken(): String? {
        return sharedPreferences.getString(tokenKey,null)
    }

    fun clearSavedCredentials() {
        editor.remove("KEY_USERNAME")
        editor.remove("KEY_TOKEN")
        editor.apply()
    }
}
