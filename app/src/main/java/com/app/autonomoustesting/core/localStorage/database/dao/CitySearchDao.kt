package com.app.autonomoustesting.core.localStorage.database.dao

import androidx.room.*
import com.app.autonomoustesting.core.model.CitySearch

@Dao
interface CitySearchDao {

    @Query("select * from CitySearch")
    suspend fun getCitySearches(): List<CitySearch>?

    @Query("select * from CitySearch where cityName=:cityName LIMIT 1")
    suspend fun getCitySearch(cityName: String): CitySearch?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCitySearch(citySearch: CitySearch)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCitySearches(citySearches: List<CitySearch>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCitySearch(citySearch: CitySearch)

    @Query("DELETE FROM CitySearch where cityName=:cityName")
    suspend fun deleteCitySearch(cityName: String)

    @Delete
    suspend fun deleteCitySearch(citySearch: CitySearch)

    @Query("DELETE FROM CitySearch")
    suspend fun deleteAllCitySearch()

}