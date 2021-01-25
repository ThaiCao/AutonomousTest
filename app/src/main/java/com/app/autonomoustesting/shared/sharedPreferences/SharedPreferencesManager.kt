package com.app.autonomoustesting.shared.sharedPreferences

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesManager  @Inject constructor(context: Context){

    var mainSharedPreferences: MainSharedPreferences = MainSharedPreferences(context)
}