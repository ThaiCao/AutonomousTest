package com.app.autonomoustesting.core.localStorage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.autonomoustesting.core.localStorage.database.dao.CityLocationDao
import com.app.autonomoustesting.core.localStorage.database.dao.CitySearchDao
import com.app.autonomoustesting.core.localStorage.database.dao.DailyWeatherDao
import com.app.autonomoustesting.core.localStorage.database.typeConverter.ModelTypeConverter
import com.app.autonomoustesting.core.model.CityLocation
import com.app.autonomoustesting.core.model.CitySearch
import com.app.autonomoustesting.core.model.DailyWeather

@Database(entities = [
    (DailyWeather::class), ( CityLocation:: class), ( CitySearch:: class)
], version = 1, exportSchema = false)

@TypeConverters(
    ModelTypeConverter::class
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun cityLocationDao(): CityLocationDao
    abstract fun weatherDailyDao(): DailyWeatherDao
    abstract fun citySearchDao(): CitySearchDao
}