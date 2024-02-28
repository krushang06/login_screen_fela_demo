package com.example.login_screen_test.home

import com.google.gson.annotations.SerializedName

data class HomeResponse (
    @SerializedName("is_error")
    var isError   : Boolean?,
    @SerializedName("message")
    var message   : String?,
    @SerializedName("page_count" )
    var pageCount : String?,
    @SerializedName("data")
    var data      : Dataas?, )

data class Dataas(
    @SerializedName("user")
    var user      : User?,
    @SerializedName("banner_images")
    var bannerImages: ArrayList<BannerImages>)

data class BannerImages (
    @SerializedName("banner_image")
    var bannerImage: BannerImage?)

data class BannerImage (
    @SerializedName("image_url")
    var imageUrl   : String? = null, )

data class User (
    @SerializedName("name")
    var name       : String?,
    @SerializedName("email")
    var email      : String?,)
