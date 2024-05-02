package com.example.login_screen_test.network

import android.content.Context
import com.example.login_screen_test.utils.PreferencesManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClientUrl {

    // clone app

    private var BASE_URL = "https://dev.fela.co/api/v1/"

    private val apiRequestResponses: MutableList<Triple<String, String, Int>> = mutableListOf()

    private fun getHttpClient(context: Context): OkHttpClient {

        val preferencesManager = PreferencesManager(context)
        val token = preferencesManager.getSavedToken()

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val headerInterceptor = Interceptor { chain ->
            val original = chain.request()

            val request: Request = if (preferencesManager.getSavedToken() == null) {
                original.newBuilder().build()
            } else {
                if (token != null) {
                    original
                        .newBuilder()
                        .header("x-session-token", token)
                        .build()
                } else {
                    original.newBuilder().build()
                }
            }

//            val requestUrl = request.url.toString()

            val response = chain.proceed(request)

//            val responseBody = response.peekBody(Long.MAX_VALUE).string()
//
//            val statusCode = response.code

//            apiRequestResponses.add(Triple(requestUrl, responseBody, statusCode))

            response
        }

        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .addInterceptor(AppMaticNetworkInterceptorr())
            .addInterceptor(headerInterceptor)
            .build()
    }

    fun getRetrofitInstance(context: Context, baseUrl: String? = null): FelaApiService {
        baseUrl?.let {
            BASE_URL = it
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient(context))
            .build()

        return retrofit.create(FelaApiService::class.java)
    }

    fun getApiRequestResponses(): List<Triple<String, String, Int>> {
        return apiRequestResponses.toList()
    }

    fun clearApiRequestResponses() {
        apiRequestResponses.clear()
    }

    }


// 19 apr
/*object RetrofitClientUrl {

    private var BASE_URL = "https://dev.fela.co/api/v1/"

    private val apiRequestResponses: MutableList<Triple<String, String, Int>> = mutableListOf()

    private fun getHttpClient(context: Context): OkHttpClient {

        val preferencesManager = PreferencesManager(context)
        val token = preferencesManager.getSavedToken()

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val headerInterceptor = Interceptor { chain ->
            val original = chain.request()

            val request: Request = if (preferencesManager.getSavedToken() == null) {
                original.newBuilder().build()
            } else {
                if (token != null) {
                    original
                        .newBuilder()
                        .header("x-session-token", token)
                        .build()
                } else {
                    original.newBuilder().build()
                }
            }

            val requestUrl = request.url.toString()

            val response = chain.proceed(request)

            val responseBody = response.peekBody(Long.MAX_VALUE).string()

            val statusCode = response.code

            apiRequestResponses.add(Triple(requestUrl, responseBody, statusCode))

            response

        }

        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    fun getRetrofitInstance(context: Context, baseUrl: String? = null): FelaApiService {
        baseUrl?.let {
            BASE_URL = it
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient(context))
            .build()

        return retrofit.create(FelaApiService::class.java)
    }

    fun getApiRequestResponses(): List<Triple<String, String, Int>> {
        return apiRequestResponses.toList()
    }

    fun clearApiRequestResponses() {
        apiRequestResponses.clear()
    }

    //System.out

}*/


// new code 17-4-24
/*object RetrofitClientUrl {

    private const val BASE_URL = "https://dev.fela.co/api/v1/"

    private fun getHttpClient(context: Context): OkHttpClient {

        val preferencesManager = PreferencesManager(context)
        val token = preferencesManager.getSavedToken()

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val headerInterceptor = Interceptor { chain ->
            val original = chain.request()

            val request: Request = if (preferencesManager.getSavedToken() == null) {
                original.newBuilder().build()
            } else {
                if (token != null) {
                    original
                        .newBuilder()
                        .header("x-session-token", token)
                        .build()
                } else {
                    original.newBuilder().build()
                }
            }

            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    fun getRetrofitInstance(context: Context): FelaApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient(context))
            .build()

        return retrofit.create(FelaApiService::class.java)
    }
}*/


// old code
/*
object RetrofitClientUrl {

    private const val BASE_URL = "https://dev.fela.co/api/v1/"

    private fun getHttpClient(context: Context): OkHttpClient {

        val preferencesManager = PreferencesManager(context)
        val token = preferencesManager.getSavedToken()

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val headerInterceptor = Interceptor { chain ->
            val original = chain.request()

            val request:Request = if(preferencesManager.getSavedToken() == null) {
                original.newBuilder().build()
            }else {
                if (token != null) {
                    original
                        .newBuilder()
                        .header("x-session-token", token)
                        .build()
                }else{
                    original.newBuilder().build()
                }
            }

            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    fun getRetrofitInstance(context: Context): FelaApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient(context))
            .client(getHttpClient(context))
            .build()

        return retrofit.create(FelaApiService::class.java)
    }

}*/
