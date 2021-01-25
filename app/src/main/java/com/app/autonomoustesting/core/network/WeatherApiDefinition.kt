package com.app.autonomoustesting.core.network

import com.app.autonomoustesting.core.model.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiDefinition {
    @GET("onecall")
    suspend fun getWeatherByLocation( @Query("lat") lat: Double,  @Query("lon") lng: Double,
                                      @Query("exclude") exclude: String,  @Query("appid") appId: String ): Response<WeatherResponse>
}