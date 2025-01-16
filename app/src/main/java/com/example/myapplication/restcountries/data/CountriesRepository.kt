package com.example.myapplication.restcountries.data

import com.example.core.network_wrapper.AppResult
import com.example.myapplication.restcountries.domain.model.CountriesDomainEntity
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {

    suspend fun getCountriesFromNetwork(): AppResult<Boolean>
     fun showAllCountries(): Flow<List<CountriesDomainEntity>>
     fun getCountriesDetails(countriesName:String): Flow<CountriesDomainEntity>
}