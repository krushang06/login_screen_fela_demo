package com.example.login_screen_test.LoginData

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val `data`: Datas,
    val is_error: Boolean,
    val message: String,
    val page_count: String
)
data class Datas(
    val allow_notifications: Int,
    val already_know: String,
    val already_know_status: Any,
    @SerializedName("email")
    val email: String,
    val id: Int,
    val is_locked: Boolean,
    val is_new_user: Boolean,
    val language_module_id: Any,
    val name: String,
    val profile_image: Any,
    val role: String,
    @SerializedName("session_token")
    val sessiontoken: String,
    val social_profiles: Any,
    val subscription_ends_at: Any
)