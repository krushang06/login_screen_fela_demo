package com.example.login_screen_test.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_screen_test.network.FelaApiService
import com.example.login_screen_test.network.RetrofitClientUrl
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel : ViewModel() {
    private val _homeResponse = MutableLiveData<Dataas?>()
    val session: LiveData<Dataas?> = _homeResponse

    private val _homeimage = MutableLiveData<List<BannerImages?>>()
    val homeimage: MutableLiveData<List<BannerImages?>> = _homeimage


    fun fetchHomeData(context: Context) {
        viewModelScope.launch {


            try {
                val retIn = RetrofitClientUrl.getRetrofitInstance(context)
                val response = retIn.fetchHomeData()

//                printAndClearApiRequestResponses()

                if (response.isError == true) {
                    _homeResponse.value = null
                    _homeimage.value = null
                } else {
                    _homeResponse.value = response.data
                    _homeimage.value = response.data?.bannerImages
                }

            } catch (e: IOException) {
                _homeResponse.value = null
            } catch (e: HttpException) {
                _homeResponse.value = null
            } catch (e: Exception) {
                _homeResponse.value = null
            }
        }
    }
//    fun printAndClearApiRequestResponses() {
//        val requestResponses = RetrofitClientUrl.getApiRequestResponses()
//
//        for ((request, response, statusCode) in requestResponses) {
//            val headers = arrayOf(requestResponses.map { it.first }.toTypedArray())
//            val httpCall = HttpCall.Builder()
//                .withRequestHeaders(buildMap { request })
//                .withResponseBody(response)
//                .withStatusText(response)
//                .withStatusCode(0)
//                .withUrl(request)
////                .headers(HttpHeader(listOf(HttpHeader("Content-Type", HttpHeaderValue("application/json"))).toString())) // Example headers
//                .build()
//
//            AndroidSnooper.instance.record(httpCall)
//        }
//        RetrofitClientUrl.clearApiRequestResponses()
//    }
}

