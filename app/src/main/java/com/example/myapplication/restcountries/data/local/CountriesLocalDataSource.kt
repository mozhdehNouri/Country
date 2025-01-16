package com.example.myapplication.restcountries.data.local

import com.example.myapplication.restcountries.data.local.entites.CountriesEntity
import kotlinx.coroutines.flow.Flow

interface CountriesLocalDataSource {

    suspend fun insertAllCountries(countriesEntity: List<CountriesEntity>)


    fun getAllCountries() : Flow<List<CountriesEntity>>

    fun getCountryDetails(countryName:String) : Flow<CountriesEntity>
}