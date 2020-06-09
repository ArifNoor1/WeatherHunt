package com.devadilarif.weatherhunt.repo.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devadilarif.weatherhunt.repo.local.dao.CovidDao
import com.devadilarif.weatherhunt.repo.local.dao.NewsDao
import com.devadilarif.weatherhunt.repo.local.model.COVID19
import com.devadilarif.weatherhunt.repo.local.model.DailyForcast
import com.devadilarif.weatherhunt.repo.local.model.News

@Database(entities  = [News::class, DailyForcast::class, COVID19::class], version = 1)
abstract class WeatherHuntDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun dailyForecastDao() : DailyForcast
    abstract fun covidDao() : CovidDao

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