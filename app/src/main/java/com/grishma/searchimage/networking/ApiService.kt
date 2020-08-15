package com.grishma.searchimage.networking

import com.grishma.searchimage.model.ImageSearch
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Api Service class
 */
interface ApiService {

    @GET("search/1")
    fun getImagesList(
        @Header("Authorization") authorization: String?,
        @Query("q") query: String?
    ): Call<ImageSearch>

    companion object {
        private const val BASE_URL = "https://api.imgur.com/3/gallery/"

        fun createService(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
