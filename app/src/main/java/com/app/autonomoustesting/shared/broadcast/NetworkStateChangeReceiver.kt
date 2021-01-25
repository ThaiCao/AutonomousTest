package com.app.autonomoustesting.shared.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.app.autonomoustesting.BaseApplication
import com.app.autonomoustesting.core.repository.AppRepository
import com.app.autonomoustesting.shared.utils.NetworkUtils
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class NetworkStateChangeReceiver : BroadcastReceiver() {

    @Inject
    lateinit var appRepository: AppRepository


    override fun onReceive(context: Context, intent: Intent) {
        val app = context!!.applicationContext as BaseApplication
        app.appComponent.inject(this)
        if (NetworkUtils(context).isNetworkAvailable()) {
            runBlocking {
                appRepository.getCachedAPI()
            }
        }
    }
}