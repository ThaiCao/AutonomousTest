package com.app.autonomoustesting.core.network.weatherApi

import com.app.autonomoustesting.core.model.DailyWeather
import com.app.autonomoustesting.core.network.WeatherApiDefinition
import com.app.autonomoustesting.core.network.utils.WeatherApiErrorHandler
import com.app.autonomoustesting.core.repository.utils.Result
import javax.inject.Inject

class WeatherApiImpl @Inject constructor(
    private val weatherApiErrorHandler: WeatherApiErrorHandler,
    private val apiDefinition: WeatherApiDefinition
) : WeatherApi {
    override suspend fun getWeather(
        lat: Double,
        lng: Double,
        exclude: String,
        appId: String
    ): Result<ArrayList<DailyWeather>> {
        return try {
            val response = apiDefinition.getWeatherByLocation(lat, lng, exclude, appId)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                if(body.daily !=null){
                    Result.Success(body.daily)
                }else{
                    Result.Error(weatherApiErrorHandler.createGeneralErrorResponse())
                }
            } else {
                val error = weatherApiErrorHandler.createErrorResponse(response.errorBody())
                Result.Error(error)
            }
        } catch (e: Exception) {
            Result.Error(weatherApiErrorHandler.createGeneralErrorResponse())
        }
    }


}