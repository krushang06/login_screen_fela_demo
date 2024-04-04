package com.example.login_screen_test.MyFavoriteGroup

data class MyFavRespons(
    val `data`: Datats,
    val is_error: Boolean,
    val message: String,
    val page_count: String,
)

data class Datats(
    val favourites: List<Favourite>,
    val know_its: List<Any>,
)

data class Favourite(
    val id: Int,
    val user_id: Int,
    val word: Wordss,
    val word_id: Int,
)

data class Wordss(
    val id: Int,
    val is_favourite: Boolean,
    val word_id: Int,
    val word: String,
    val word_translation: WordTranslation,
)

data class WordTranslation(
    val id: Int,
    var translation: String,
    val word_id: Int,
)


