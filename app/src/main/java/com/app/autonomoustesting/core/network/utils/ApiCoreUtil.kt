package com.app.autonomoustesting.core.network.utils

import com.app.autonomoustesting.R
import android.content.Context
import com.app.autonomoustesting.BuildConfig
import okhttp3.Headers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiCoreUtil @Inject constructor(
    localeProvider: LocaleProvider,
    context: Context
) {

    val locationBaseUrl = BuildConfig.LOCATION_BASE_URL
    val locationVersion = BuildConfig.LOCATION_URL_VERSION
    val weatherBaseUrl = BuildConfig.WEATHER_BASE_URL
    val weatherVersion = BuildConfig.WEATHER_URL_VERSION

    private val accessTokenKey: String = context.resources.getString(R.string.access_token_key)
    private val clientLocaleKey: String = context.resources.getString(R.string.client_locale_key)
    private val contentTypeKey: String = context.resources.getString(R.string.content_type_key)
    private val acceptKey: String = context.resources.getString(R.string.accept_key)
    private val connectionKey: String = context.resources.getString(R.string.connection_key)

    private val contentTypeValue: String = context.resources.getString(R.string.content_type_value)
    private val acceptValue: String = context.resources.getString(R.string.accept_value)
    private val connectionValue: String = context.resources.getString(R.string.connection_value)
    private val clientLocaleValue: String = localeProvider.locale

    val headers: Headers
        get() {
            val headerBuilder = Headers.Builder()
                .add(clientLocaleKey, clientLocaleValue)
                .add(contentTypeKey, contentTypeValue)
                .add(acceptKey, acceptValue)
                .add(connectionKey, connectionValue)

            return headerBuilder.build()
        }

}