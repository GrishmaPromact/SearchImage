package com.grishma.searchimage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.grishma.getarticles.utils.Converters
import com.grishma.searchimage.model.ImageSearch

/**
 * Image Search Database initialization
 */
@Database(
    entities = [ImageSearch.Data::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ImageSearchDb : RoomDatabase() {

  companion object {
    fun create(context: Context): ImageSearchDb {
      val databaseBuilder = Room.databaseBuilder(context, ImageSearchDb::class.java, "imageSearch.db")
      return databaseBuilder.build()
    }
  }

  abstract fun imageSearchDao(): ImageSearchDao
}
