package com.example.login_screen_test.network

import com.example.login_screen_test.LoginData.LoginRequest
import com.example.login_screen_test.LoginData.LoginResponse
import com.example.login_screen_test.WordSets.WordSets
import com.example.login_screen_test.WordSets.Wordset
import com.example.login_screen_test.home.HomeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface FelaApiService {

    @POST("signin")
    suspend fun fetchData(@Body email: LoginRequest, ): Response<LoginResponse>

    @GET("home-data")
    suspend fun fetchHomeData(): HomeResponse

    @GET("categories")
    suspend fun getCategories(): Wordset

    @GET("categories/{categoryId}/words")
    suspend fun getCategoryWords(): Wordset

}