package com.app.autonomoustesting.core.localStorage.database.dao

import androidx.room.*
import com.app.autonomoustesting.core.model.DailyWeather

@Dao
interface DailyWeatherDao {

    @Query("select * from DailyWeather")
    suspend fun getWeatherDaily(): List<DailyWeather>?

    @Query("select * from DailyWeather where cityName=:cityName")
    suspend fun getWeatherDaily(cityName: String): List<DailyWeather>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherDaily(dailyWeather: DailyWeather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherDaily(dailyWeatherList: List<DailyWeather>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWeatherDaily(dailyWeather: DailyWeather)

    @Query("DELETE FROM DailyWeather where cityName=:cityName")
    suspend fun deleteWeatherDaily(cityName: String)

    @Delete
    suspend fun deleteWeatherDaily(dailyWeather: DailyWeather)

    @Query("DELETE FROM DailyWeather")
    suspend fun deleteAllWeatherDaily()
}