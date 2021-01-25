package com.app.autonomoustesting.di.modules

import com.app.autonomoustesting.core.network.LocationApiDefinition
import com.app.autonomoustesting.core.network.WeatherApiDefinition
import com.app.autonomoustesting.core.network.utils.ApiCoreUtil
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideLocationApiDefinition(@Named("locationRetrofit") retrofit: Retrofit): LocationApiDefinition {
        return retrofit.create(LocationApiDefinition::class.java)
    }

    @Singleton
    @Provides
    @Named("locationRetrofit")
    fun provideLocationRetrofit(
        client: OkHttpClient,
        jsonConverter: Converter.Factory,
        apiCoreUtil: ApiCoreUtil
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiCoreUtil.locationBaseUrl + apiCoreUtil.locationVersion)
            .addConverterFactory(jsonConverter)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherApiDefinition(@Named("weatherRetrofit") retrofit: Retrofit): WeatherApiDefinition {
        return retrofit.create(WeatherApiDefinition::class.java)
    }

    @Singleton
    @Provides
    @Named("weatherRetrofit")
    fun provideWeatherRetrofit(
        client: OkHttpClient,
        jsonConverter: Converter.Factory,
        apiCoreUtil: ApiCoreUtil
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiCoreUtil.weatherBaseUrl + apiCoreUtil.weatherVersion)
            .addConverterFactory(jsonConverter)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideJsonConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @Named("headersInterceptor") headersInterceptor: Interceptor,
        @Named("errorHandlerInterceptor") errorHandlerInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headersInterceptor)
            .addInterceptor(errorHandlerInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        return interceptor
    }

    @Singleton
    @Provides
    @Named("headersInterceptor")
    fun provideHeadersInterceptor(apiCoreUtil: ApiCoreUtil): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.headers(apiCoreUtil.headers)
            chain.proceed(requestBuilder.build())
        }
    }

    @Singleton
    @Provides
    @Named("errorHandlerInterceptor")
    fun createErrorHandlerInterceptor(): Interceptor {
        return Interceptor { chain ->
            //interceptor for error handling
            val request = chain.request()
            val response = chain.proceed(request)
            response
        }
    }
}