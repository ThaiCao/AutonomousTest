package com.app.autonomoustesting.core.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
@Keep
@Parcelize
data class ErrorMessage(val message: String) : Parcelable