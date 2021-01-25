package com.app.autonomoustesting.features.main

import androidx.annotation.Keep
import com.app.autonomoustesting.shared.ui.recyclerView.BaseModel
@Keep
class CityModel (
    override val cellId: String,
    val cityName: String?
) : BaseModel