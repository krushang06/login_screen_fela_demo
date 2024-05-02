package com.example.login_screen_test.FavoriteWordGroups

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_screen_test.WordSets.Dataword
import com.example.login_screen_test.network.RetrofitClientUrl
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class WordGroupViewModel : ViewModel() {
    private val _favwordgroup = MutableLiveData<List<Datat?>>()
    val favwordgroup: MutableLiveData<List<Datat?>> = _favwordgroup

    fun fachwordsets(context: Context){
        viewModelScope.launch {
            try {
                val retin = RetrofitClientUrl.getRetrofitInstance(context)
                val response = retin.getFavWordGroup()
                printAndClearApiRequestResponses()

                if (response.is_error == false) {
                    _favwordgroup.value = response.data
                } else {
                    _favwordgroup.value = null
                }
            } catch (e: IOException)    {
                _favwordgroup.value = null
            } catch (e: HttpException) {
                _favwordgroup.value = null
            } catch (e: Exception)     {
                _favwordgroup.value = null
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
    }

}