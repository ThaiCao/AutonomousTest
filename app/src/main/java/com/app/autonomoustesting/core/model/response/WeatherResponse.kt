package com.app.autonomoustesting.core.model.response

import androidx.annotation.Keep
import com.app.autonomoustesting.core.model.DailyWeather
@Keep
data class WeatherResponse(val daily: ArrayList<DailyWeather>, val cityName: String)