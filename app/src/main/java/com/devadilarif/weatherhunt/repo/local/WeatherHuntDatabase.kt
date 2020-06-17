package com.devadilarif.weatherhunt.repo.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devadilarif.weatherhunt.repo.local.dao.CovidDao
import com.devadilarif.weatherhunt.repo.local.dao.ForecastDao
import com.devadilarif.weatherhunt.repo.local.dao.NewsDao
import com.devadilarif.weatherhunt.repo.local.dao.WeatherResponseDao
import com.devadilarif.weatherhunt.repo.local.model.COVID19
import com.devadilarif.weatherhunt.repo.local.model.Forcast
import com.devadilarif.weatherhunt.repo.local.model.News
import com.devadilarif.weatherhunt.repo.local.model.WeatherResponse
import com.devadilarif.weatherhunt.repo.local.typeconverters.ListTypeConverter

@Database(entities  = [News::class, Forcast::class, COVID19::class, WeatherResponse::class], version = 1)
@TypeConverters(ListTypeConverter::class)
abstract class WeatherHuntDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun forecastDao() : ForecastDao
    abstract fun covidDao() : CovidDao
    abstract fun currentWeatherDao() : WeatherResponseDao

    companion object{
        const val DB_NAME  = "weatherhunt.db"

        @Volatile
        private var INSTANCE : WeatherHuntDatabase? = null

        fun getInstance(context : Context) : WeatherHuntDatabase{
            if(INSTANCE == null){
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WeatherHuntDatabase::class.java,
                        DB_NAME
                    ).build()
                    return INSTANCE!!
                }
            }
            return INSTANCE!!
        }

    }
}