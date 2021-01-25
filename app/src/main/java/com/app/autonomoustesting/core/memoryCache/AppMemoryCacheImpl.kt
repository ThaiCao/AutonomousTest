package com.app.autonomoustesting.core.memoryCache

import androidx.lifecycle.MutableLiveData
import com.app.autonomoustesting.core.memoryCache.utils.MemoryCacheErrorHandler
import com.app.autonomoustesting.core.model.CityLocation
import com.app.autonomoustesting.core.model.response.WeatherResponse
import com.app.autonomoustesting.core.repository.utils.Result
import javax.inject.Inject

class AppMemoryCacheImpl  @Inject constructor(
    private val errorHandler: MemoryCacheErrorHandler
) : AppMemoryCache {

    private val cityLocationListLiveData: MutableLiveData<ArrayList<CityLocation>> = MutableLiveData()
    private val weatherListLiveData: MutableLiveData<ArrayList<WeatherResponse>> = MutableLiveData()


    override suspend fun overrideCityLocations(cityLocations: ArrayList<CityLocation>?) {
        cityLocations?.let {
            cityLocationListLiveData.value = it
        }
    }

    override suspend fun insertCityLocations(cityLocation: CityLocation) {
       val cityLocations = cityLocationListLiveData.value
        cityLocations?.add(cityLocation)
        cityLocations?.let {
            cityLocationListLiveData.value = it
        }
    }

    override suspend fun getCityLocations(): Result<ArrayList<CityLocation>> {
        cityLocationListLiveData.value.let { cityLocations ->
            return if (cityLocations != null) {
                Result.Success(cityLocations)
            } else {
                Result.Error(errorHandler.createGeneralError())
            }
        }
    }

    override suspend fun getCityLocations(cityName: String): Result<CityLocation> {
        val repo = cityLocationListLiveData.value?.find { it.cityName == cityName }
        return if (repo != null) {
            Result.Success(repo)
        } else {
            Result.Error(errorHandler.createGeneralError())
        }
    }

    override suspend fun isCityLocationsExist(cityName: String): Boolean {
        val repo = cityLocationListLiveData.value?.find { it.cityName == cityName }
        return repo != null
    }

    override suspend fun overrideWeathers(weathers: ArrayList<WeatherResponse>?) {
        weathers?.let {
            weatherListLiveData.value = it
        }
    }

    override suspend fun insertWeathers(weather: WeatherResponse) {
        val weatherResponses = weatherListLiveData.value
        weatherResponses?.add(weather)
        weatherResponses?.let {
            weatherListLiveData.value = it
        }
    }

    override suspend fun getWeathers(): Result<ArrayList<WeatherResponse>> {
        weatherListLiveData.value.let { weathers ->
            return if (weathers != null) {
                Result.Success(weathers)
            } else {
                Result.Error(errorHandler.createGeneralError())
            }
        }
    }

    override suspend fun getWeather(cityName: String): Result<WeatherResponse> {
        val repo = weatherListLiveData.value?.find { it.cityName == cityName }
        return if (repo != null) {
            Result.Success(repo)
        } else {
            Result.Error(errorHandler.createGeneralError())
        }
    }
}