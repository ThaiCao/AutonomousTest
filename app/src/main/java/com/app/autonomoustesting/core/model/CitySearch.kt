package com.app.autonomoustesting.core.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
@Entity
data class CitySearch(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var cityName: String
) : Parcelable