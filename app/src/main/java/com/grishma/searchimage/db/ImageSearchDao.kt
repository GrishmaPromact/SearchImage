package com.grishma.searchimage.db

import androidx.room.*
import com.grishma.searchimage.model.ImageSearch

/**
 * ImageSearchDao class for Images
 */
@Dao
interface ImageSearchDao {

  //insert data into db
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(posts: MutableList<ImageSearch.Data>)

  //get all images from db
  @Query("SELECT * FROM data")
  fun getImages(): List<ImageSearch.Data>

  //update item to db
  @Update
  fun update(posts: ImageSearch.Data)

}
