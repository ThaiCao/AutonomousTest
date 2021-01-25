package com.app.autonomoustesting.core.memoryCache

import com.app.autonomoustesting.core.model.CityLocation
import com.app.autonomoustesting.core.model.response.WeatherResponse
import com.app.autonomoustesting.core.repository.utils.Result

interface AppMemoryCache {
    // city location
    suspend fun overrideCityLocations(cityLocations: ArrayList<CityLocation>?)

    suspend fun insertCityLocations(cityLocation: CityLocation)

    suspend fun getCityLocations(): Result<ArrayList<CityLocation>>

    suspend fun isCityLocationsExist(cityName: String): Boolean

    suspend fun getCityLocations(cityName: String): Result<CityLocation>

    // weather

    suspend fun overrideWeathers(weathers: ArrayList<WeatherResponse>?)

    suspend fun insertWeathers(weather: WeatherResponse)

    suspend fun getWeathers(): Result<ArrayList<WeatherResponse>>

    suspend fun getWeather(cityName: String): Result<WeatherResponse>
}