package com.devadilarif.weatherhunt.repo.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devadilarif.weatherhunt.repo.local.model.COVID19
import io.reactivex.Single

@Dao
interface CovidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCovid19(covid19: COVID19)

    @Delete
    fun deleteCovid19(covid19: COVID19)

    @Query("SELECT * FROM COVID19")
    fun getCovidUpdates(): LiveData<COVID19>

}