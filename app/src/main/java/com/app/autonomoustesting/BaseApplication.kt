package com.app.autonomoustesting

import android.app.Application
import com.app.autonomoustesting.di.components.AppComponent
import com.app.autonomoustesting.di.components.DaggerAppComponent

class BaseApplication : Application(){

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

}