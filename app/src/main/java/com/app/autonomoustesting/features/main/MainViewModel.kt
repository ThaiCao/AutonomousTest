package com.app.autonomoustesting.features.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.autonomoustesting.core.model.CityLocation
import com.app.autonomoustesting.core.model.DailyWeather
import com.app.autonomoustesting.core.repository.AppRepository
import com.app.autonomoustesting.core.repository.utils.Result
import com.app.autonomoustesting.di.scopes.ActivityScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor(  private val appRepository: AppRepository) : ViewModel(){

    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    val weatherModelLiveData: MutableLiveData<ArrayList<DailyWeatherModel>> = MutableLiveData()

    val cityModelLiveData: MutableLiveData<ArrayList<CityModel>> = MutableLiveData()

    val errorCityLiveData: MutableLiveData<String> = MutableLiveData()

    fun getWeatherData(cityName: String){
        viewModelScope.launch {
            loadingLiveData.value = true
            appRepository.getCityLocation(cityName).let {result ->
                loadingLiveData.value = false
                if (result is Result.Success) {
                    weatherModelLiveData.value = createWeatherModels(result.data)
                } else if (result is Result.Error) {
                    errorLiveData.value = result.error.message
                }
            }
        }
    }

    fun isDataAvailable() = weatherModelLiveData.value != null

    private fun createWeatherModels(repos: ArrayList<DailyWeather>?): ArrayList<DailyWeatherModel> {
        val itemModels = ArrayList<DailyWeatherModel>()
        var position = 0
        repos?.forEach {
            itemModels.add(createWeatherModel(it, position))
            position ++
        }
        return itemModels
    }

    private fun createWeatherModel(repo: DailyWeather, position: Int): DailyWeatherModel {
        return DailyWeatherModel(
            cellId = position.toString(),
            dateTime = repo.dateTime,
            sunrise = repo.sunrise,
            sunset = repo.sunset,
            pressure = repo.pressure,
            humidity = repo.humidity,
            windSpeed = repo.windSpeed,
            clouds = repo.clouds,
            weathers = repo.weather,
            temp = repo.temp
        )
    }


    fun getCityAutoComplete(){
        viewModelScope.launch {
            appRepository.getCityLocation().let {result ->
                if (result is Result.Success) {
                    cityModelLiveData.value = createCityLocationModels(result.data)
                } else if (result is Result.Error) {
                    errorCityLiveData.value = result.error.message
                }
            }
        }
    }

    private fun createCityLocationModels(repos: ArrayList<CityLocation>?): ArrayList<CityModel> {
        val itemModels = ArrayList<CityModel>()
        var position = 0
        repos?.forEach {
            itemModels.add(createCityLocationModel(it, position))
            position ++
        }
        return itemModels
    }


    private fun createCityLocationModel(repo: CityLocation, position: Int): CityModel {
        return CityModel(
            cellId = position.toString(),
            cityName = repo.cityName
        )
    }


    fun resetAllData(){
        viewModelScope.launch {
            appRepository.resetAllData()
        }
    }
}