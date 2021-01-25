package com.app.autonomoustesting.core.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
@Keep
@Parcelize
data class WeatherFeelsLike(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) : Parcelable