package com.app.autonomoustesting.features.main

import androidx.annotation.Keep
import com.app.autonomoustesting.core.model.WeatherItem
import com.app.autonomoustesting.core.model.WeatherTemp
import com.app.autonomoustesting.shared.ui.recyclerView.BaseModel
@Keep
class DailyWeatherModel(
    override val cellId: String,
    val dateTime: Long,
    val sunrise: Long,
    val sunset: Long,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val clouds: Int,
    val weathers: ArrayList<WeatherItem>?,
    val temp: WeatherTemp
) : BaseModel