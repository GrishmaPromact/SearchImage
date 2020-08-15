package com.grishma.searchimage.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grishma.searchimage.model.ImageSearch
import com.grishma.searchimage.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * ImageSearchViewModel
 */
class ImageSearchViewModel : ViewModel() {

    private var apiClient : ApiService = ApiService.createService()
    private val apiImageSearchData: MutableLiveData<Pair<Boolean,ImageSearch>> = MutableLiveData()
    private val apiError: MutableLiveData<Throwable?> = MutableLiveData()
    private val loading: MutableLiveData<Boolean?> = MutableLiveData()

    fun apiImageSearchData(): MutableLiveData<Pair<Boolean,ImageSearch>>{
        return apiImageSearchData
    }

    fun apiError(): MutableLiveData<Throwable?>? {
        return apiError
    }

    fun loading(): MutableLiveData<Boolean?>? {
        return loading
    }


    //getImages from API
    fun getAllImages(authorization: String, query: String?) {
        loading.postValue(true)
        apiClient.getImagesList(authorization, query)
            .enqueue(object : Callback<ImageSearch> {
                override fun onFailure(
                    call: Call<ImageSearch>?,
                    t: Throwable
                ) {
                    Log.e("TAG::", "Failed to load data!")
                    apiError.postValue(t)
                    loading.postValue(false)
                }

                override fun onResponse(
                    call: Call<ImageSearch>?,
                    response: Response<ImageSearch>
                ) {
                    Log.e("TAG::", "Successfully load data!")
                    val posts = response.body()
                    apiImageSearchData.postValue(Pair(true,posts!!))
                    loading.postValue(false)
                }
            })
    }


    override fun onCleared() {
        super.onCleared()
    }


}



