package com.example.login_screen_test.GroupListCreate

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_screen_test.CategoriesWords.CategoryWords
import com.example.login_screen_test.CategoriesWords.FavoriteRequest
import com.example.login_screen_test.network.RetrofitClientUrl
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class GroupListCreateViewModel : ViewModel() {
//    private val _glcvm = MutableLiveData<List<CategoryWords?>>()
//    val glcvm: MutableLiveData<List<CategoryWords?>> = _glcvm
//
//    private val _favoriteWord = MutableLiveData<Boolean>()
//    val favoriteWord: MutableLiveData<Boolean> = _favoriteWord
//
//    fun fetchCategories(context: Context, id: Int) {
//        viewModelScope.launch {
//            try {
//                val retin = RetrofitClientUrl.getRetrofitInstance(context)
//                val response = retin.getCategoryWords(id)
//
//                if (response.isError == false) {
//                    _glcvm.value = response.data?.categoryWords
//                } else {
//                    _glcvm.value = null
//                }
//            } catch (e: IOException)    {
//                _glcvm.value = null
//            } catch (e: HttpException) {
//                _glcvm.value = null
//            } catch (e: Exception)     {
//                _glcvm.value = null
//            }
//
//        }
//    }
//
//    fun fetchFavourite(favoriteData: FavoriteRequest, context: Context) {
//        viewModelScope.launch {
//            try {
//                val retin = RetrofitClientUrl.getRetrofitInstance(context)
//                val response = retin.getFavourite(favoriteData)
//
//                if (response.isSuccessful) {
//                    response.body()
//                    _favoriteWord.value = true
//
//                } else {
//                    _favoriteWord.value = false
//                }
//
//            } catch (e: IOException) {
//                _favoriteWord.value = null
//            }
//            catch (e: HttpException) {
//                _favoriteWord.value = null
//            }
//            catch (e: Exception) {
//                _favoriteWord.value = null
//            }
//        }
//    }
}
