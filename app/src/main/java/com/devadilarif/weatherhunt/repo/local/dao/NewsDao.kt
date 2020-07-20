package com.devadilarif.weatherhunt.repo.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devadilarif.weatherhunt.repo.local.model.News
import io.reactivex.Single

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(news : News)

    @Delete
    fun deleteNews(news : News)

    @Query("SELECT * FROM News")
    fun getAllNews(): LiveData<List<News>>

    //ORDER BY timeStamp DESC
    @Query("SELECT * FROM News LIMIT 3")
    fun getTopNews(): LiveData<List<News>>


    @Query("SELECT * FROM News WHERE publishedAt = :publishedAt")
    fun getNews(publishedAt : String) : News

    @Update
    fun updateNews(news : News)


    @Query("SELECT * FROM News WHERE isBookmarked = 1")
    fun getBookmarkedNews(): LiveData<List<News>>

}