package com.devadilarif.weatherhunt.repo.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devadilarif.weatherhunt.repo.local.model.News
import io.reactivex.Single

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news : News)

    @Delete
    fun deleteNews(news : News)

    @Query("SELECT * FROM News")
    fun getAllNews(): LiveData<List<News>>

    //ORDER BY timeStamp DESC
    @Query("SELECT * FROM News LIMIT 3")
    fun getTopNews(): LiveData<List<News>>

}