package com.app.autonomoustesting.core.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
@Keep
@Parcelize
@Entity
data class DailyWeather(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var cityName: String?,
    @SerializedName("dt")
    var dateTime: Long,
    var sunrise: Long,
    var sunset: Long,
    var temp: WeatherTemp,
    @SerializedName("feels_like")
    var feelsLike: WeatherFeelsLike,
    var pressure: Int,
    var humidity: Int,
    @SerializedName("dew_point")
    var dewPoint: Double,
    @SerializedName("wind_speed")
    var windSpeed: Double,
    @SerializedName("wind_deg")
    var windDeg: Double,
    var weather: ArrayList<WeatherItem>?,
    var clouds: Int,
    var pop: Double,
    var rain: Double,
    var uvi: Double
) : Parcelable