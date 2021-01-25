package com.app.autonomoustesting.core.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
@Keep
@Parcelize
data class WeatherItem(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) : Parcelable