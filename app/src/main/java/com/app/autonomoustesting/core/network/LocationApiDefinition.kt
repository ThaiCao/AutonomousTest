package com.app.autonomoustesting.core.network

import com.app.autonomoustesting.core.model.response.CityLocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiDefinition {
    @GET("json")
    suspend fun getLocationByCityName(@Query("q") cityName: String, @Query("key") key: String, @Query("pretty") pretty: Int,
                                      @Query("no_annotations") noAnnotations: Int, @Query("no_dedupe") noDedupe: Int,
                                      @Query("no_record") noRecord: Int, @Query("litmit") litmit: Int): Response<CityLocationResponse>
}