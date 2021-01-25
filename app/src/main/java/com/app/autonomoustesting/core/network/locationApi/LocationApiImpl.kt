package com.app.autonomoustesting.core.network.locationApi

import com.app.autonomoustesting.core.model.CityLocation
import com.app.autonomoustesting.core.network.LocationApiDefinition
import com.app.autonomoustesting.core.network.utils.LocationApiErrorHandler
import com.app.autonomoustesting.core.repository.utils.Result
import javax.inject.Inject

class LocationApiImpl @Inject constructor(
    private val locationApiErrorHandler: LocationApiErrorHandler,
    private val apiDefinition: LocationApiDefinition
) : LocationApi  {

    override suspend fun getCityLocation(cityName: String, key: String, pretty: Int, noAnnotations: Int,
                                         noDedupe: Int, noRecord: Int, litmit: Int): Result<CityLocation> {
        return try {
            val response = apiDefinition.getLocationByCityName(cityName, key, pretty, noAnnotations, noDedupe, noRecord, litmit)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                if(body.results !=null && body.results.size >0 && body.results[0].geometry !=null){
                    val cityLocation = CityLocation(0, cityName, body.results[0].geometry.lat, body.results[0].geometry.lng)
                    Result.Success(cityLocation)
                }else{
                    Result.Error(locationApiErrorHandler.createGeneralErrorResponse())
                }
            } else {
                val error = locationApiErrorHandler.createErrorResponse(response.errorBody())
                Result.Error(error)
            }
        } catch (e: Exception) {
            Result.Error(locationApiErrorHandler.createGeneralErrorResponse())
        }
    }
}