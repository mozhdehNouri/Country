package com.example.myapplication.restcountries.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.myapplication.restcountries.data.local.entites.CountriesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountriesDao {

    @Upsert
    suspend fun insertAllCountries(countriesEntity: List<CountriesEntity>)


    @Query("select * from countriesentity")
    fun getAllCountries() : Flow<List<CountriesEntity>>

    @Query("select * from countriesentity where name == :countryName")
    fun getCountryDetails(countryName:String) : Flow<CountriesEntity>

}