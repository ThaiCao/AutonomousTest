package com.app.autonomoustesting.core.localStorage.storage

import com.app.autonomoustesting.core.localStorage.database.dao.CityLocationDao
import com.app.autonomoustesting.core.localStorage.database.dao.CitySearchDao
import com.app.autonomoustesting.core.localStorage.database.dao.DailyWeatherDao
import com.app.autonomoustesting.core.localStorage.utils.StorageErrorHandler
import com.app.autonomoustesting.core.model.CityLocation
import com.app.autonomoustesting.core.model.CitySearch
import com.app.autonomoustesting.core.model.DailyWeather
import com.app.autonomoustesting.core.repository.utils.Result
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppStorageImpl @Inject constructor(
    private val errorHandler: StorageErrorHandler,
    private val cityLocationDao: CityLocationDao,
    private val dailyWeatherDao: DailyWeatherDao,
    private val citySearchDao: CitySearchDao
) : AppStorage {
    override suspend fun insertCityLocation(cityLocations: CityLocation) {
        cityLocationDao.insertCityLocation(cityLocations)
    }

    override suspend fun getCityLocations(): Result<ArrayList<CityLocation>> {
        val cityLocations = cityLocationDao.getCityLocation()
        return if (cityLocations != null) {
            Result.Success(ArrayList(cityLocations))
        } else {
            Result.Error(errorHandler.createGeneralError())
        }
    }

    override suspend fun getCityLocations(cityName: String): Result<CityLocation> {
        val repo = cityLocationDao.getCityLocation(cityName)
        return if (repo != null) {
            Result.Success(repo)
        } else {
            Result.Error(errorHandler.createGeneralError())
        }
    }

    override suspend fun isCityLocationsExist(cityName: String): Boolean {
        val repo = cityLocationDao.getCityLocation(cityName)
        return repo != null
    }

    override suspend fun deleteCityLocations(cityName: String) {
        cityLocationDao.deleteCityLocation(cityName)
    }

    override suspend fun deleteCityLocations() {
        cityLocationDao.deleteAllCityLocation()
    }

    override suspend fun insertWeather(weather: DailyWeather) {
        dailyWeatherDao.insertWeatherDaily(weather)
    }

    override suspend fun getWeathers(): Result<ArrayList<DailyWeather>> {
        val weatherDailyList = dailyWeatherDao.getWeatherDaily()
        return if (weatherDailyList != null) {
            Result.Success(ArrayList(weatherDailyList))
        } else {
            Result.Error(errorHandler.createGeneralError())
        }
    }

    override suspend fun getWeather(
        cityName: String
    ): Result<ArrayList<DailyWeather>> {
        val repo = dailyWeatherDao.getWeatherDaily(cityName)
        return if (repo != null) {
            Result.Success(ArrayList(repo))
        } else {
            Result.Error(errorHandler.createGeneralError())
        }
    }

    override suspend fun deleteWeather(cityName: String) {
        dailyWeatherDao.deleteWeatherDaily(cityName)
    }

    override suspend fun deleteWeather() {
        dailyWeatherDao.deleteAllWeatherDaily()
    }

    override suspend fun insertCitySearch(citySearch: CitySearch) {
        citySearchDao.insertCitySearch(citySearch)
    }

    override suspend fun getCitySearches(): Result<ArrayList<CitySearch>> {
        val citySearchList = citySearchDao.getCitySearches()
        return if (citySearchList != null) {
            Result.Success(ArrayList(citySearchList))
        } else {
            Result.Error(errorHandler.createGeneralError())
        }
    }

    override suspend fun getCitySearch(cityName: String): Result<CitySearch> {
        val repo = citySearchDao.getCitySearch(cityName)
        return if (repo != null) {
            Result.Success(repo)
        } else {
            Result.Error(errorHandler.createGeneralError())
        }
    }

    override suspend fun deleteCitySearch(cityName: String) {
        citySearchDao.deleteCitySearch(cityName)
    }

    override suspend fun deleteCitySearches() {
       citySearchDao.deleteAllCitySearch()
    }


}