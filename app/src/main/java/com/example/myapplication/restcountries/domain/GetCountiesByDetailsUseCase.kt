package com.example.myapplication.restcountries.domain

import com.example.myapplication.restcountries.data.CountriesRepository
import com.example.myapplication.restcountries.domain.model.CountriesDomainEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCountiesByDetailsUseCase @Inject constructor(private val repository: CountriesRepository) {
    operator fun invoke(countriesName: String): Flow<CountriesDomainEntity> =
        repository.getCountriesDetails(countriesName)
}