package com.devadilarif.weatherhunt.repo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devadilarif.weatherhunt.repo.local.model.DailyForcast
import io.reactivex.Single

@Dao
interface DailyForecastDao {

    @Insert
    fun insertForecast(dailyForcast: DailyForcast)

    @Query("SELECT * FROM DailyForcast ORDER BY dt LIMIT 7")
    fun getWeeklyForecast(): Single<List<DailyForcast>>

    
}