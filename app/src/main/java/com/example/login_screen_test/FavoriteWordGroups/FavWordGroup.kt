package com.example.login_screen_test.FavoriteWordGroups

data class FavWordGroup(
    val `data`: List<Datat>,
    val is_error: Boolean,
    val message: String,
    val page_count: Int
)
data class Datat(
    val created_at: String,
    val id: Int,
    val language_module_id: Int,
    val name: String,
    val updated_at: String,
    val user_id: Int,
    val words_count: Int
)