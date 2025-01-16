package com.example.myapplication.restcountries.domain

import com.example.core.network_wrapper.AppResult
import com.example.myapplication.restcountries.data.CountriesRepository
import javax.inject.Inject

class FetchAllCountriesFromNetworkUseCase @Inject constructor(private val repository: CountriesRepository){
    suspend operator fun invoke() : AppResult<Boolean> = repository.getCountriesFromNetwork()
}