package com.example.login_screen_test.CategoriesWords

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_screen_test.WordSets.CategoryImage
import com.example.login_screen_test.WordSets.Dataword
import kotlinx.coroutines.launch

class CategoriesWordsViewModel : ViewModel() {

    private val _categoriesword = MutableLiveData<List<Dataword?>>()
    val CategoriesWord: MutableLiveData<List<Dataword?>> get() = _categoriesword

    internal fun getCompletedTaskById(id: Int): List<Dataword?>? {
        viewModelScope.launch {
            val retrievedList = getCompletedTaskById(id)
            _categoriesword.postValue(retrievedList)
        }
        return listOf(Dataword())
    }
}

