package com.example.login_screen_test.network

import android.annotation.SuppressLint
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AppMaticNetworkInterceptorr : Interceptor {

    companion object {
        private const val TAG = "ShakeNetworkInterceptor"
    }

    @SuppressLint("LogNotTimber")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d(TAG, "Request: ${request.method} ${request.url}")

        request.headers.forEach { (key, value) ->
            Log.d(TAG, "Request Header: $key: $value")
        }

        val response = chain.proceed(request)
        Log.d(TAG, "Request Response: ${response.code} ${response.message}")

        response.headers.forEach { (key, value) ->
            Log.d(TAG, "Response Header: $key: $value")
        }
        Log.d(TAG, "Response Body: ${response.body?.string()}")
        Log.d(TAG, "Response Headers: ${response.headers}")

        return response
    }
}
