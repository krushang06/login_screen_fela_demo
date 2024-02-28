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

}
