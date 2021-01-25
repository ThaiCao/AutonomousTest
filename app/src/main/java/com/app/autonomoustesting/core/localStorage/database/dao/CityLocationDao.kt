package com.app.autonomoustesting.core.localStorage.database.dao

import androidx.room.*
import com.app.autonomoustesting.core.model.CityLocation

@Dao
interface CityLocationDao {

    @Query("select * from CityLocation")
    suspend fun getCityLocation(): List<CityLocation>?

    @Query("select * from CityLocation where cityName=:cityName LIMIT 1")
    suspend fun getCityLocation(cityName: String): CityLocation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityLocation(cityLocation: CityLocation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityLocation(cityLocations: List<CityLocation>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCityLocation(cityLocation: CityLocation)

    @Query("DELETE FROM CityLocation where cityName=:cityName")
    suspend fun deleteCityLocation(cityName: String)

    @Delete
    suspend fun deleteCityLocation(cityLocation: CityLocation)

    @Query("DELETE FROM CityLocation")
    suspend fun deleteAllCityLocation()
}