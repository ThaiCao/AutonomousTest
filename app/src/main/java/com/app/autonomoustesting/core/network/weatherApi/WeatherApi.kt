package com.app.autonomoustesting.core.network.weatherApi

import com.app.autonomoustesting.core.model.DailyWeather
import com.app.autonomoustesting.core.repository.utils.Result

interface WeatherApi {
    suspend fun getWeather(lat: Double, lng: Double,exclude: String, appId: String): Result<ArrayList<DailyWeather>>
}