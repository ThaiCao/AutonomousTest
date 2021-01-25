package com.app.autonomoustesting.shared.utils

import android.content.Context
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateUtil @Inject constructor() {

    companion object {
        const val standardFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        const val completeDateFormat = "yyyy-MM-dd HH:mm"
        const val yearShortMonthShortDayFormat = "yyyy-MM-dd"
        const val yearMonthShortDayFormat = "yyyy. MMMM d."
        const val detailedMonthDayFormat = "dd MMMM yyyy"
        const val dayFormat = "EEEE"
        const val hourMinuteFormat = "HH:mm"
        const val monthFormat = "MMM"
        const val simpleDayFormat = "d"
    }

    fun getCurrentDate(): Date {
        return Date()
    }

    @Suppress("DEPRECATION")
    fun getLocaleFromContext(context: Context?): Locale {
        val configuration = context?.resources?.configuration
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration?.locales?.get(0) ?: Locale.getDefault()
        } else {
            configuration?.locale ?: Locale.getDefault()
        }
    }

    fun toSimpleString(date: Date) : String {
        val format = SimpleDateFormat(yearShortMonthShortDayFormat)
        return format.format(date)
    }

    fun toFormatDateToString(date: Date, format: String) : String {
        val format = SimpleDateFormat(format)
        return format.format(date)
    }
}