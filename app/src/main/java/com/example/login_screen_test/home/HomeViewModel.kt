package com.example.login_screen_test.home

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_screen_test.network.RetrofitClientUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import kotlin.time.Duration.Companion.seconds

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
}

