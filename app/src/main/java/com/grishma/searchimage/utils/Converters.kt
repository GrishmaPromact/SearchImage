package com.grishma.getarticles.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.grishma.searchimage.model.ImageSearch

/**
 * Converter class
 */
class Converters {
    @TypeConverter
    fun listToJson(value: List<ImageSearch.Data>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<ImageSearch.Data>::class.java).toList()


    @TypeConverter
    fun listToJson1(value: List<ImageSearch.Data.Image?>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList1(value: String) = Gson().fromJson(value, Array<ImageSearch.Data.Image?>::class.java).toList()

    @TypeConverter
    fun listToJson2(value: List<ImageSearch.Data.Tag>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList2(value: String) = Gson().fromJson(value, Array<ImageSearch.Data.Tag>::class.java).toList()
}