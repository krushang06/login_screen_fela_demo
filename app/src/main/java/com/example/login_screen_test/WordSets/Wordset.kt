package com.example.login_screen_test.WordSets

import com.google.gson.annotations.SerializedName

data class Wordset(
    @SerializedName("is_error")
    var isError: Boolean? = null,

    @SerializedName("data")
    var Dataword: ArrayList<Dataword> = arrayListOf(),

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("page_count")
    var pageCount: Int? = null

)

data class CategoryImage(

    @SerializedName("id") var id: Int? = null,

    @SerializedName("file_name") var fileName: String? = null,

    @SerializedName("file_url") var fileUrl: String? = null,

    @SerializedName("image_url")
    var imageUrl: String? = null,

    @SerializedName("thumb_image_url") var thumbImageUrl: String? = null,

    @SerializedName("video_url") var videoUrl: String? = null,

    @SerializedName("audio_url") var audioUrl: String? = null,

    @SerializedName("video_duration") var videoDuration: String? = null

)

data class Dataword(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("language_module_id")
    var languageModuleId: Int? = null,

    @SerializedName("user_id")
    var userId: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("created_by")
    var createdBy: String? = null,

    @SerializedName("category_words_count")
    var categoryWordsCount: Int? = null,

    @SerializedName("is_locked")
    var isLocked: Boolean? = null,

    @SerializedName("category_image")
    var categoryImage: CategoryImage? = CategoryImage(),

    @SerializedName("category_word_ids")
    var categoryWordIds: ArrayList<Int> = arrayListOf(),

    @SerializedName("choose_translation_word_ids")
    var chooseTranslationWordIds: ArrayList<Int> = arrayListOf(),

    @SerializedName("choose_audio_translation_word_ids")
    var chooseAudioTranslationWordIds: ArrayList<Int> = arrayListOf(),

    @SerializedName("true_false_word_ids")
    var trueFalseWordIds: ArrayList<Int> = arrayListOf(),

    @SerializedName("word_pronunciation_word_ids")
    var wordPronunciationWordIds: ArrayList<Int> = arrayListOf(),

    @SerializedName("collect_letter_word_ids")
    var collectLetterWordIds: ArrayList<Int> = arrayListOf()

)

