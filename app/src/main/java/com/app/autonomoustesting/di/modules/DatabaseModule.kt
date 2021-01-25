package com.app.autonomoustesting.di.modules

import android.content.Context
import androidx.room.Room
import com.app.autonomoustesting.BuildConfig
import com.app.autonomoustesting.core.localStorage.database.AppDatabase
import com.app.autonomoustesting.core.localStorage.database.dao.CityLocationDao
import com.app.autonomoustesting.core.localStorage.database.dao.CitySearchDao
import com.app.autonomoustesting.core.localStorage.database.dao.DailyWeatherDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        val databaseName = BuildConfig.DATABASE_NAME
        return Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
    }

    @Singleton
    @Provides
    fun provideCityLocationDao(database: AppDatabase): CityLocationDao {
        return database.cityLocationDao()
    }

    @Singleton
    @Provides
    fun provideWeatherDailyDao(database: AppDatabase): DailyWeatherDao {
        return database.weatherDailyDao()
    }

    @Singleton
    @Provides
    fun provideCitySearchDao(database: AppDatabase): CitySearchDao {
        return database.citySearchDao()
    }
}