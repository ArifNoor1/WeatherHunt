package com.devadilarif.weatherhunt.repo.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devadilarif.weatherhunt.repo.local.model.WeatherResponse

@Dao
interface WeatherResponseDao {

    @Query("SELECT * FROM WeatherResponse")
    fun getCurrentWeather() : LiveData<WeatherResponse>


    //TODO: Add conflict strategy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(currentWeatherResponse: WeatherResponse)
}