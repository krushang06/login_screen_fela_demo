package com.example.login_screen_test.network

import com.example.login_screen_test.CategoriesWords.CtgsWords
import com.example.login_screen_test.CategoriesWords.FavoriteRequest
import com.example.login_screen_test.FavoriteWordGroups.FavWordGroup
import com.example.login_screen_test.GroupListCreate.GLCRequest
import com.example.login_screen_test.LoginData.LoginRequest
import com.example.login_screen_test.LoginData.LoginResponse
import com.example.login_screen_test.MyFavoriteGroup.MyFavRespons
import com.example.login_screen_test.MyFavoriteGroup.creategrouplist
import com.example.login_screen_test.WordSets.Wordset
import com.example.login_screen_test.home.HomeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FelaApiService {

    @POST("signin")
    suspend fun fetchData(@Body email: LoginRequest): Response<LoginResponse>

    @GET("home-data")
    suspend fun fetchHomeData(): HomeResponse

    @GET("categories")
    suspend fun getCategories(): Wordset

    @GET("categories/{categoryId}/words")
    suspend fun getCategoryWords(@Path("categoryId") categoryId: Int): CtgsWords

    @POST("favourite/word")
    suspend fun getFavourite(@Body word: FavoriteRequest): Response<CtgsWords>

    @GET("group-lists")
    suspend fun getFavWordGroup(): FavWordGroup

    @GET("favourite-knownit-words")
    suspend fun getFavKnowItWords(): MyFavRespons

    @POST("group-list/create")
    suspend fun getcreateFavWordGroup(@Body word_id: GLCRequest): Response<creategrouplist>

}