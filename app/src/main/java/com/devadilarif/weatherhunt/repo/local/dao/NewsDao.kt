package com.devadilarif.weatherhunt.repo.local.dao

import androidx.room.*
import com.devadilarif.weatherhunt.repo.local.model.News
import io.reactivex.Single

@Dao
interface NewsDao {

    @Insert
    fun insertNews(news : News)

    @Delete
    fun deleteNews(news : News)

    @Query("SELECT * FROM News")
    fun getAllNews(): Single<List<News>>

    @Query("SELECT * FROM News ORDER BY timeStamp DESC LIMIT 3")
    fun getTopNews(): Single<List<News>>





}