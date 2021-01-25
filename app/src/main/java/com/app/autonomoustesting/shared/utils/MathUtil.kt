package com.app.autonomoustesting.shared.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MathUtil @Inject constructor() {

    fun roundOffDecimal(number: Double): Double? {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }
}