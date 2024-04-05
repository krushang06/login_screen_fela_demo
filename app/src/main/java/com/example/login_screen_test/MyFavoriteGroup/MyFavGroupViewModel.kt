package com.example.login_screen_test.MyFavoriteGroup

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_screen_test.GroupListCreate.GLCRequest
import com.example.login_screen_test.network.RetrofitClientUrl
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MyFavGroupViewModel : ViewModel() {

    private val _myfavgroupword = MutableLiveData<List<Favourite?>>()
    val myfavgroupWordss: MutableLiveData<List<Favourite?>> = _myfavgroupword

    private val _createlistgroup = MutableLiveData<Boolean>()
    val createlistgroup: MutableLiveData<Boolean> = _createlistgroup

    // get myfavgroup api calling
    fun fetchmyfavgroup(context: Context) {
        viewModelScope.launch {
            try {
                val retin = RetrofitClientUrl.getRetrofitInstance(context)
                val response = retin.getFavKnowItWords()

                if (!response.is_error) {
                    _myfavgroupword.value = response.data.favourites

                } else {
                    _myfavgroupword.value = null
                }
            } catch (e: IOException) {
                _myfavgroupword.value = null
            } catch (e: HttpException) {
                _myfavgroupword.value = null
            } catch (e: Exception) {
                _myfavgroupword.value = null
            }
        }
    }

    //create group api calling
    fun fetchcreategroup(glcRequest: GLCRequest, context: Context) {
        viewModelScope.launch {
            try {
                val retin = RetrofitClientUrl.getRetrofitInstance(context)
                val response = retin.getcreateFavWordGroup(glcRequest)
                // call on this model cheke the response
                if (response.isSuccessful) {
                    response.body()
                    _createlistgroup.value = true
                } else {
                    _createlistgroup.value = false
                }
            } catch (e: IOException) {
                _createlistgroup.value = null
            } catch (e: HttpException) {
                _createlistgroup.value = null
            } catch (e: Exception) {
                _createlistgroup.value = null
            }
        }
    }

}
