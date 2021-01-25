package com.app.autonomoustesting.shared.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class MainSharedPreferences(context: Context)  {

    private val prefKey = "mainPrefKey"
    private val dateSearchKey = "date_search"
    private val sharedPreferences: SharedPreferences

    var dateSearch: String
        get() = sharedPreferences.getString(dateSearchKey, "").toString()
        set(dateSearch) {
            sharedPreferences.edit {
                putString(dateSearchKey, dateSearch)
            }
        }


    init {
        sharedPreferences = context.getSharedPreferences(prefKey, Context.MODE_PRIVATE)
    }
}