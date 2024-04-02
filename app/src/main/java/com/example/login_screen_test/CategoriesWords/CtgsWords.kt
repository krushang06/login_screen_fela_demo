package com.example.login_screen_test.CategoriesWords

import com.google.gson.annotations.SerializedName

data class CtgsWords(
    @SerializedName("is_error") var isError: Boolean? = null,
    @SerializedName("data") var data: Data? = Data(),
    @SerializedName("message") var message: String? = null,
    @SerializedName("page_count") var pageCount: String? = null
)
data class Data (

    @SerializedName("id"                 ) var id               : Int?                     = null,
    @SerializedName("language_module_id" ) var languageModuleId : Int?                     = null,
    @SerializedName("user_id"            ) var userId           : Int?                     = null,
    @SerializedName("name"               ) var name             : String?                  = null,
    @SerializedName("exercise_type"      ) var exerciseType     : String?                  = null,
    @SerializedName("created_by"         ) var createdBy        : String?                  = null,
    @SerializedName("is_paid"            ) var isPaid           : Int?                     = null,
    @SerializedName("category_image"     ) var categoryImage    : CategoryImage?           = CategoryImage(),
    @SerializedName("category_words"     ) var categoryWords    : ArrayList<CategoryWords> = arrayListOf()

)
data class CategoryImage (

    @SerializedName("id"              ) var id            : Int?    = null,
    @SerializedName("file_name"       ) var fileName      : String? = null,
    @SerializedName("file_url"        ) var fileUrl       : String? = null,
    @SerializedName("image_url"       ) var imageUrl      : String? = null,
    @SerializedName("thumb_image_url" ) var thumbImageUrl : String? = null,
    @SerializedName("video_url"       ) var videoUrl      : String? = null,
    @SerializedName("audio_url"       ) var audioUrl      : String? = null,
    @SerializedName("video_duration"  ) var videoDuration : String? = null

)
data class WordNouns (

    @SerializedName("id"      ) var id     : Int?    = null,
    @SerializedName("word_id" ) var wordId : Int?    = null,
    @SerializedName("type"    ) var type   : String? = null,
    @SerializedName("value"   ) var value  : String? = null

)

data class WordTenses (

    @SerializedName("id"      ) var id     : Int?    = null,
    @SerializedName("word_id" ) var wordId : Int?    = null,
    @SerializedName("type"    ) var type   : String? = null,
    @SerializedName("value"   ) var value  : String? = null

)
data class WordNounTranslations (

    @SerializedName("id"      ) var id     : Int?    = null,
    @SerializedName("word_id" ) var wordId : Int?    = null,
    @SerializedName("value"   ) var value  : String? = null

)
data class WordAdjectiveTranslations (

    @SerializedName("id"      ) var id     : Int?    = null,
    @SerializedName("word_id" ) var wordId : Int?    = null,
    @SerializedName("value"   ) var value  : String? = null

)

data class WordVerbTranslations (

    @SerializedName("id"      ) var id     : Int?    = null,
    @SerializedName("word_id" ) var wordId : Int?    = null,
    @SerializedName("value"   ) var value  : String? = null

)

data class WordAdverbTranslations (

    @SerializedName("id"      ) var id     : Int?    = null,
    @SerializedName("word_id" ) var wordId : Int?    = null,
    @SerializedName("value"   ) var value  : String? = null

)
data class WordPronounTranslations (

    @SerializedName("id"      ) var id     : Int?    = null,
    @SerializedName("word_id" ) var wordId : Int?    = null,
    @SerializedName("value"   ) var value  : String? = null

)

data class WordInterjectionTranslations (

    @SerializedName("id"      ) var id     : Int?    = null,
    @SerializedName("word_id" ) var wordId : Int?    = null,
    @SerializedName("value"   ) var value  : String? = null

)
data class WordConjunctionTranslations (

    @SerializedName("id"      ) var id     : Int?    = null,
    @SerializedName("word_id" ) var wordId : Int?    = null,
    @SerializedName("value"   ) var value  : String? = null

)data class WordPrepositionTranslations (

    @SerializedName("id"      ) var id     : Int?    = null,
    @SerializedName("word_id" ) var wordId : Int?    = null,
    @SerializedName("value"   ) var value  : String? = null

)
data class WordArticleTranslations (

    @SerializedName("id"      ) var id     : Int?    = null,
    @SerializedName("word_id" ) var wordId : Int?    = null,
    @SerializedName("value"   ) var value  : String? = null

)
data class WordTranslation (

    @SerializedName("id"          ) var id          : Int?    = null,
    @SerializedName("word_id"     ) var wordId      : Int?    = null,
    @SerializedName("translation" ) var translation : String? = null

)
data class WordSentences (

    @SerializedName("id"          ) var id          : Int?    = null,
    @SerializedName("word_id"     ) var wordId      : Int?    = null,
    @SerializedName("original"    ) var original    : String? = null,
    @SerializedName("translation" ) var translation : String? = null

)data class WordAudio (

    @SerializedName("id"              ) var id            : Int?    = null,
    @SerializedName("file_name"       ) var fileName      : String? = null,
    @SerializedName("file_url"        ) var fileUrl       : String? = null,
    @SerializedName("image_url"       ) var imageUrl      : String? = null,
    @SerializedName("thumb_image_url" ) var thumbImageUrl : String? = null,
    @SerializedName("video_url"       ) var videoUrl      : String? = null,
    @SerializedName("audio_url"       ) var audioUrl      : String? = null,
    @SerializedName("video_duration"  ) var videoDuration : String? = null

)
data class CategoryWords (

    @SerializedName("id"                               ) var id                            : Int?                                    ,
    @SerializedName("language_module_id"               ) var languageModuleId              : Int?                                    = null,
    @SerializedName("word"                             ) var word                          : String?                                 = null,
    @SerializedName("is_infinitive"                    ) var isInfinitive                  : Int?                                    = null,
    @SerializedName("pronunciation"                    ) var pronunciation                 : String?                                 = null,
    @SerializedName("created_by"                       ) var createdBy                     : String?                                 = null,
    @SerializedName("created_at"                       ) var createdAt                     : String?                                 = null,
    @SerializedName("updated_at"                       ) var updatedAt                     : String?                                 = null,
    @SerializedName("word_nouns"                       ) var wordNouns                     : ArrayList<WordNouns>                    = arrayListOf(),
    @SerializedName("word_tenses"                      ) var wordTenses                    : ArrayList<WordTenses>                   = arrayListOf(),
    @SerializedName("word_noun_translations"           ) var wordNounTranslations          : ArrayList<WordNounTranslations>         = arrayListOf(),
    @SerializedName("word_adjective_translations"      ) var wordAdjectiveTranslations     : ArrayList<WordAdjectiveTranslations>    = arrayListOf(),
    @SerializedName("word_verb_translations"           ) var wordVerbTranslations          : ArrayList<WordVerbTranslations>         = arrayListOf(),
    @SerializedName("word_adverb_translations"         ) var wordAdverbTranslations        : ArrayList<WordAdverbTranslations>       = arrayListOf(),
    @SerializedName("word_pronoun_translations"        ) var wordPronounTranslations       : ArrayList<WordPronounTranslations>      = arrayListOf(),
    @SerializedName("word_interjection_translations"   ) var wordInterjectionTranslations  : ArrayList<WordInterjectionTranslations> = arrayListOf(),
    @SerializedName("word_conjunction_translations"    ) var wordConjunctionTranslations   : ArrayList<WordConjunctionTranslations>  = arrayListOf(),
    @SerializedName("word_preposition_translations"    ) var wordPrepositionTranslations   : ArrayList<WordPrepositionTranslations>  = arrayListOf(),
    @SerializedName("word_article_translations"        ) var wordArticleTranslations       : ArrayList<WordArticleTranslations>      = arrayListOf(),
    @SerializedName("is_favourite"                     ) var isFavourite                   : Boolean?                                = null,
    @SerializedName("is_know_it"                       ) var isKnowIt                      : Boolean?                                = null,
    @SerializedName("updated_at_favourite"             ) var updatedAtFavourite            : String?                                 = null,
    @SerializedName("updated_at_know_it"               ) var updatedAtKnowIt               : String?                                 = null,
    @SerializedName("parts_speech_translations_orders" ) var partsSpeechTranslationsOrders : ArrayList<Int>                          = arrayListOf(),
    @SerializedName("word_translation"                 ) var wordTranslation               : WordTranslation?                        = WordTranslation(),
    @SerializedName("word_sentences"                   ) var wordSentences                 : ArrayList<WordSentences>                = arrayListOf(),
    @SerializedName("word_audio"                       ) var wordAudio                     : WordAudio?                              = WordAudio()
)



