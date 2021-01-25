package com.app.autonomoustesting.core.localStorage.storage

import com.app.autonomoustesting.core.model.CityLocation
import com.app.autonomoustesting.core.model.CitySearch
import com.app.autonomoustesting.core.model.DailyWeather
import com.app.autonomoustesting.core.repository.utils.Result

interface AppStorage {
    // city location
    suspend fun insertCityLocation(cityLocation: CityLocation)

    suspend fun getCityLocations(): Result<ArrayList<CityLocation>>

    suspend fun isCityLocationsExist(cityName: String): Boolean

    suspend fun getCityLocations(cityName: String): Result<CityLocation>

    suspend fun deleteCityLocations(cityName: String)

    suspend fun deleteCityLocations()

    // weather
    suspend fun insertWeather(weather: DailyWeather)

    suspend fun getWeathers(): Result<ArrayList<DailyWeather>>

    suspend fun getWeather(cityName: String): Result<ArrayList<DailyWeather>>

    suspend fun deleteWeather(cityName: String)

    suspend fun deleteWeather()

    //city search
    suspend fun insertCitySearch(citySearch: CitySearch)

    suspend fun getCitySearches(): Result<ArrayList<CitySearch>>

    suspend fun getCitySearch(cityName: String): Result<CitySearch>

    suspend fun deleteCitySearch(cityName: String)

    suspend fun deleteCitySearches()
}