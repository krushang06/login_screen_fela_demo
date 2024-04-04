package com.example.login_screen_test.MyFavoriteGroup

data class creategrouplist(
    val `data`: Datagroup,
    val is_error: Boolean,
    val message: String,
    val page_count: String
)
data class Datagroup(
    val created_at: String,
    val group_words: List<GroupWord>,
    val id: Int,
    val language_module_id: Int,
    val name: String,
    val updated_at: String,
    val user_id: Int
)
data class GroupWord(
    val created_at: String,
    val deleted_at: Any,
    val group_list_id: Int,
    val id: Int,
    val updated_at: String,
    val word_id: Int
)