package com.app.autonomoustesting.di.modules

import com.app.autonomoustesting.core.network.locationApi.LocationApi
import com.app.autonomoustesting.core.network.locationApi.LocationApiImpl
import com.app.autonomoustesting.core.network.weatherApi.WeatherApi
import com.app.autonomoustesting.core.network.weatherApi.WeatherApiImpl
import dagger.Binds
import dagger.Module


@Module
abstract class ApiModule {

    @Binds
    abstract fun provideLocationApi(locationApi: LocationApiImpl): LocationApi

    @Binds
    abstract fun provideWeatherApi(weatherApi: WeatherApiImpl): WeatherApi

}