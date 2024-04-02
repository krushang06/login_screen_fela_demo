package com.example.login_screen_test.MyFavoriteGroup

data class MyFavRespons(
    val `data`: Datats,
    val is_error: Boolean,
    val message: String,
    val page_count: String
)
data class Datats(
    val favourites: List<Favourite>,
    val know_its: List<Any>
)
data class Favourite(
    val id: Int,
    val user_id: Int,
    val word: Wordss,
    val word_id: Int
)
data class Wordss(
    val choose_translation_word_options: List<ChooseTranslationWordOption>,
    val created_at: String,
    val created_by: String,
    val id: Int,
    val is_favourite: Boolean,
    val is_infinitive: Int,
    val language_module_id: Int,
    val parts_speech_translations_orders: List<Int>,
    val pronunciation: String,
    val updated_at: String,
    val updated_at_favourite: String,
    val word: String,
    val word_adjective_translations: List<WordAdjectiveTranslation>,
    val word_adverb_translations: List<WordAdverbTranslation>,
    val word_article_translations: List<WordArticleTranslation>,
    val word_audio: WordAudio,
    val word_conjunction_translations: List<WordConjunctionTranslation>,
    val word_interjection_translations: List<WordInterjectionTranslation>,
    val word_noun_translations: List<WordNounTranslation>,
    val word_nouns: List<WordNoun>,
    val word_preposition_translations: List<WordPrepositionTranslation>,
    val word_pronoun_translations: List<WordPronounTranslation>,
    val word_sentences: List<WordSentence>,
    val word_tenses: List<WordTense>,
    val word_translation: WordTranslation,
    val word_verb_translations: List<WordVerbTranslation>
)
data class WordTranslation(
    val id: Int,
    val translation: String,
    val word_id: Int
)
data class WordAdjectiveTranslation(
    val id: Int,
    val value: String,
    val word_id: Int,
)
data class ChooseTranslationWordOption(
    val choose_translation_word_id: Int,
    val is_correct: Int,
    val translation: String
)
data class WordVerbTranslation(
    val id: Int,
    val value: String,
    val word_id: Int
)
data class WordAdverbTranslation(
    val id: Int,
    val value: String,
    val word_id: Int
)
data class WordArticleTranslation(
    val id: Int,
    val value: String,
    val word_id: Int
)
data class WordAudio(
    val audio_url: String,
    val file_name: String,
    val file_url: String,
    val id: Int,
    val image_url: String,
    val thumb_image_url: String,
    val video_duration: Any,
    val video_url: String
)
data class WordConjunctionTranslation(
    val id: Int,
    val value: String,
    val word_id: Int
)
data class WordInterjectionTranslation(
    val id: Int,
    val value: String,
    val word_id: Int
)
data class WordNoun(
    val id: Int,
    val type: String,
    val value: String,
    val word_id: Int
)
data class WordNounTranslation(
    val id: Int,
    val value: String,
    val word_id: Int
)
data class WordPrepositionTranslation(
    val id: Int,
    val value: String,
    val word_id: Int
)
data class WordPronounTranslation(
    val id: Int,
    val value: String,
    val word_id: Int
)
data class WordSentence(
    val id: Int,
    val value: String,
    val word_id: Int
)
data class WordTense(
    val id: Int,
    val value: String,
    val word_id: Int
)


