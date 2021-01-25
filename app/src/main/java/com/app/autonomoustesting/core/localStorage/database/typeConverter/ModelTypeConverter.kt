package com.app.autonomoustesting.core.localStorage.database.typeConverter

import androidx.room.TypeConverter
import com.app.autonomoustesting.core.model.WeatherFeelsLike
import com.app.autonomoustesting.core.model.WeatherItem
import com.app.autonomoustesting.core.model.WeatherTemp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ModelTypeConverter {
    private val jsonConverter = Gson()

    @TypeConverter
    fun weatherTempToString(weatherTemp: WeatherTemp?): String? {
        if (weatherTemp == null) {
            return null
        }
        return jsonConverter.toJson(weatherTemp)
    }

    @TypeConverter
    fun stringToWeatherTemp(data: String?): WeatherTemp? {
        if (data == null) {
            return null
        }
        val type = object : TypeToken<WeatherTemp>() {}.type
        return jsonConverter.fromJson(data, type)
    }

    @TypeConverter
    fun weatherFeelsLikeToString(weatherFeelsLike: WeatherFeelsLike?): String? {
        if (weatherFeelsLike == null) {
            return null
        }
        return jsonConverter.toJson(weatherFeelsLike)
    }

    @TypeConverter
    fun stringToWeatherFeelsLike(data: String?): WeatherFeelsLike? {
        if (data == null) {
            return null
        }
        val type = object : TypeToken<WeatherFeelsLike>() {}.type
        return jsonConverter.fromJson(data, type)
    }

    @TypeConverter
    fun weatherItemToString(weatherItems: List<WeatherItem>?): String? {
        if (weatherItems == null) {
            return null
        }
        return jsonConverter.toJson(weatherItems)
    }

    @TypeConverter
    fun stringToWeatherItem(data: String?): ArrayList<WeatherItem>? {
        if (data == null) {
            return null
        }
        val type = object : TypeToken<ArrayList<WeatherItem>>() {}.type
        return jsonConverter.fromJson(data, type)
    }
}