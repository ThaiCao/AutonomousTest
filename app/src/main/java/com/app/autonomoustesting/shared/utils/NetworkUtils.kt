package com.app.autonomoustesting.shared.utils

import android.content.Context
import com.app.autonomoustesting.shared.broadcast.ServiceManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUtils  @Inject constructor(context: Context)  {
    private val serviceManager = ServiceManager(context)
    fun isNetworkAvailable(): Boolean {
        return serviceManager.isNetworkAvailable
    }
}