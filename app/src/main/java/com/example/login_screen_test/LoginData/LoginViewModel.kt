package com.example.login_screen_test.LoginData

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_screen_test.utils.PreferencesManager
import com.example.login_screen_test.network.RetrofitClientUrl
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchJoke(loginData: LoginRequest, context: Context) {
            viewModelScope.launch {
                try {
                    val retIn = RetrofitClientUrl.getRetrofitInstance(context)
                    val response = retIn.fetchData(loginData)
                    PreferencesManager(context).setToken(response.body()?.data!!.sessiontoken)

                    if (response.isSuccessful) {
                        // Process successful response
                        val responseData = response.body()?.data?.sessiontoken
                        _token.value = responseData
                        _error.value = response.body()?.message + " "
                    } else {
                        // Process error response
                        val errorBodyString = response.errorBody()?.string()
                        _error.value = response.body()?.message + " " + errorBodyString
                    }

                } catch (e: Exception) {
                    // Handle exceptions
                    _error.postValue("Please enter correct email and password.")
                    e.printStackTrace()
                }
            }
    }

    fun clearToken() {
        _token.value = null
    }
}