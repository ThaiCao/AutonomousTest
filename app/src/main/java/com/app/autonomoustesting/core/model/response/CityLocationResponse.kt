package com.app.autonomoustesting.core.model.response

import androidx.annotation.Keep
import com.app.autonomoustesting.core.model.LocationItem
@Keep
data class CityLocationResponse(val results: ArrayList<LocationItem>)