package com.app.autonomoustesting.core.repository

import android.util.Log
import com.app.autonomoustesting.BaseApplication
import com.app.autonomoustesting.BuildConfig
import com.app.autonomoustesting.core.localStorage.storage.AppStorage
import com.app.autonomoustesting.core.memoryCache.AppMemoryCache
import com.app.autonomoustesting.core.model.CityLocation
import com.app.autonomoustesting.core.model.CitySearch
import com.app.autonomoustesting.core.model.DailyWeather
import com.app.autonomoustesting.core.model.response.WeatherResponse
import com.app.autonomoustesting.core.network.locationApi.LocationApi
import com.app.autonomoustesting.core.network.weatherApi.WeatherApi
import com.app.autonomoustesting.core.repository.utils.RepositoryErrorHandler
import com.app.autonomoustesting.core.repository.utils.Result
import com.app.autonomoustesting.shared.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val memoryCache: AppMemoryCache,
    private val localStorage: AppStorage,
    private val locationAPI: LocationApi,
    private val weatherApi: WeatherApi,
    private val errorHandler: RepositoryErrorHandler,
    private val networkUtils: NetworkUtils
) : AppRepository {

    override suspend fun getCityLocation(cityName: String): Result<ArrayList<DailyWeather>> {
        if (memoryCache.isCityLocationsExist(cityName)) {
            // get from cache
            return onGetDailyWeatherFromCache(cityName)
        } else if (localStorage.isCityLocationsExist(cityName)) {
            // get from  local storage
            return onGetDailyWeatherFromStorage(cityName)
        } else {
            // get from network
            return onGetDailyWeatherFromNetwork(cityName)
        }
    }

    override suspend fun getCityLocation(): Result<ArrayList<CityLocation>> {
        return localStorage.getCityLocations()
    }

    override suspend fun resetAllData() {
        memoryCache.overrideCityLocations(ArrayList())
        memoryCache.overrideWeathers(ArrayList())
        localStorage.deleteWeather()
        localStorage.deleteCityLocations()
    }

    override suspend fun getCachedAPI() {
        localStorage.getCitySearches().let {result ->
            if(result is Result.Success){
                val citySearches = result.data

                for (citySearch in citySearches) { //&& networkUtils.isNetworkAvailable()
                    GlobalScope.launch {
                        withContext(Dispatchers.IO) {
                            locationAPI.getCityLocation(citySearch.cityName, BuildConfig.LOCATION_API_KEY, 1, 1, 1, 1, 1)
                                .let { resultCityLocation ->
                                    when (resultCityLocation) {
                                        is Result.Success -> {
                                            weatherApi.getWeather(
                                                resultCityLocation.data.latitude,
                                                resultCityLocation.data.longtitude,
                                                "hourly,current,minutely,alerts",
                                                BuildConfig.WEATHER_API_KEY
                                            ).let { result ->
                                                return@let when (result) {
                                                    is Result.Success -> {
                                                        // save to cache
                                                        try {
                                                            memoryCache.insertCityLocations(resultCityLocation.data)
                                                            val dailyWeathers: ArrayList<DailyWeather> = result.data
                                                            for (weather in dailyWeathers) {
                                                                weather.cityName = citySearch.cityName
                                                            }
                                                            memoryCache.insertWeathers(WeatherResponse(dailyWeathers,citySearch.cityName))
                                                        } catch (ex: Exception) {
                                                            ex.printStackTrace()
                                                        }

                                                        // save to storage
                                                        try {
                                                            localStorage.insertCityLocation(resultCityLocation.data)
                                                            for (weather in result.data) {
                                                                weather.cityName = citySearch.cityName
                                                                localStorage.insertWeather(weather)
                                                            }
                                                        } catch (ex: Exception) {
                                                            ex.printStackTrace()
                                                        }

                                                        try{
                                                            localStorage.deleteCitySearch(citySearch.cityName)
                                                        }catch (ex: Exception){
                                                            ex.printStackTrace()
                                                        }
                                                    }
                                                    else -> {

                                                    }
                                                }
                                            }

                                        }
                                        else ->{}
                                    }
                                }
                        }
                    }

                }
            }else if( result is Result.Error){
            }
        }
    }

    /**
     * get daily weather  from cache with city name
     */
    private suspend fun onGetDailyWeatherFromCache(cityName: String): Result<ArrayList<DailyWeather>> {
        memoryCache.getCityLocations(cityName).let { result ->
            when (result) {
                is Result.Success -> {
                    memoryCache.getWeather(cityName).let { result ->
                        return when (result) {
                            is Result.Success -> {
                                Result.Success(result.data.daily)
                            }
                            is Result.Error -> {
                                Result.Error(result.error)
                            }
                        }
                    }
                }
                is Result.Error -> {
                    return Result.Error(result.error)
                }
            }
        }
    }

    /**
     * get daily weather  from storage with city name
     */
    private suspend fun onGetDailyWeatherFromStorage(cityName: String): Result<ArrayList<DailyWeather>> {
        localStorage.getCityLocations(cityName).let { result ->
            when (result) {
                is Result.Success -> {
                    localStorage.getWeather(cityName).let { result ->
                        return when (result) {
                            is Result.Success -> {
                                Result.Success(result.data)
                            }
                            is Result.Error -> {
                                Result.Error(result.error)
                            }
                        }
                    }
                }
                is Result.Error -> {
                    return Result.Error(result.error)
                }
            }
        }
    }

    /**
     * get daily weather  from storage with city name
     */
    private suspend fun onGetDailyWeatherFromNetwork(cityName: String): Result<ArrayList<DailyWeather>> {
        if (networkUtils.isNetworkAvailable()) {
            locationAPI.getCityLocation(cityName, BuildConfig.LOCATION_API_KEY, 1, 1, 1, 1, 1)
                .let { resultCityLocation ->
                    when (resultCityLocation) {
                        is Result.Success -> {
                            if (networkUtils.isNetworkAvailable()) {
                                weatherApi.getWeather(
                                    resultCityLocation.data.latitude,
                                    resultCityLocation.data.longtitude,
                                    "hourly,current,minutely,alerts",
                                    BuildConfig.WEATHER_API_KEY
                                ).let { result ->
                                    return when (result) {
                                        is Result.Success -> {
                                            // save to cache
                                            try {
                                                memoryCache.insertCityLocations(resultCityLocation.data)
                                                val dailyWeathers: ArrayList<DailyWeather> = result.data
                                                for (weather in dailyWeathers) {
                                                    weather.cityName = cityName
                                                }
                                                memoryCache.insertWeathers(WeatherResponse(dailyWeathers,cityName))
                                            } catch (ex: Exception) {
                                                ex.printStackTrace()
                                            }

                                            // save to storage
                                            try {
                                                localStorage.insertCityLocation(resultCityLocation.data)
                                                for (weather in result.data) {
                                                    weather.cityName = cityName
                                                    localStorage.insertWeather(weather)
                                                }
                                            } catch (ex: Exception) {
                                                ex.printStackTrace()
                                            }

                                            Result.Success(result.data)
                                        }
                                        is Result.Error -> {
                                            Result.Error(result.error)
                                        }
                                    }
                                }
                            } else {
                                localStorage.insertCitySearch(CitySearch(0, cityName))
                                return Result.Error(errorHandler.createGeneralError())
                            }

                        }
                        is Result.Error -> {
                            return Result.Error(resultCityLocation.error)
                        }
                        else -> {
                            return Result.Error(errorHandler.createGeneralError())
                        }
                    }
                }
        } else {
            localStorage.insertCitySearch(CitySearch(0, cityName))
            return Result.Error(errorHandler.createGeneralError())
        }
    }
}