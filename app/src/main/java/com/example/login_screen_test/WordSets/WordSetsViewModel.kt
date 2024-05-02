package com.example.login_screen_test.WordSets

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.login_screen_test.home.BannerImages
import com.example.login_screen_test.home.Dataas
import com.example.login_screen_test.network.RetrofitClientUrl
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class WordSetsViewModel : ViewModel() {

    private val _categoryimage = MutableLiveData<List<Dataword?>>()
    val categoryimage: MutableLiveData<List<Dataword?>> = _categoryimage


    fun fachwordsets(context: Context){
        viewModelScope.launch {
            try {
                val retin = RetrofitClientUrl.getRetrofitInstance(context)
                val response = retin.getCategories()
                printAndClearApiRequestResponses()

                if (response.isError == false) {
                    _categoryimage.value = response.Dataword
                } else {
                    _categoryimage.value = null
                }

            } catch (e:IOException)    {
                _categoryimage.value = null
            } catch (e: HttpException) {
                _categoryimage.value = null
            } catch (e: Exception)     {
                _categoryimage.value = null
            }

        }
    }
    fun printAndClearApiRequestResponses() {
        val requestResponses = RetrofitClientUrl.getApiRequestResponses()
        for ((request, response, statusCode) in requestResponses) {
            println("Request: $request")
            println("Response: $response")
            println("Status Code : $statusCode")
            println("---------------------------------")
        }
        // Clear stored API request-response pairs (if needed)
        RetrofitClientUrl.clearApiRequestResponses()
        println("API Request-Response pairs cleared")
    }}