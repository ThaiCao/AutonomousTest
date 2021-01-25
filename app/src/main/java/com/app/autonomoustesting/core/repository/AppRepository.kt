package com.app.autonomoustesting.core.repository

import com.app.autonomoustesting.core.model.CityLocation
import com.app.autonomoustesting.core.model.DailyWeather
import com.app.autonomoustesting.core.repository.utils.Result

interface AppRepository {

    suspend fun getCityLocation(city: String): Result<ArrayList<DailyWeather>>

    suspend fun getCityLocation(): Result<ArrayList<CityLocation>>

    suspend fun resetAllData()

    suspend fun getCachedAPI()
}