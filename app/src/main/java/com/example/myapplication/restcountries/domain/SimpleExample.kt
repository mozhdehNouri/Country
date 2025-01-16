package com.example.myapplication.restcountries.domain

import com.example.myapplication.restcountries.data.CountriesRepository
import com.example.myapplication.restcountries.domain.model.CountriesDomainEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SimpleExample @Inject constructor(private val repository: CountriesRepository){

     suspend fun pushReceived() :  Flow<List<CountriesDomainEntity>>{
         return repository.showAllCountries()

    }

}