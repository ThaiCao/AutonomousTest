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
data class CityLocation(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var cityName: String,
    @SerializedName("lat")
    var latitude: Double,
    @SerializedName("long")
    var longtitude: Double
) : Parcelable