package com.app.autonomoustesting.core.network.locationApi

import com.app.autonomoustesting.core.model.CityLocation
import com.app.autonomoustesting.core.model.response.CityLocationResponse
import com.app.autonomoustesting.core.repository.utils.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {

    suspend fun getCityLocation(cityName: String, key: String, pretty: Int, noAnnotations: Int,
                                noDedupe: Int, noRecord: Int, litmit: Int): Result<CityLocation>?
}