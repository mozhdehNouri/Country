package com.example.myapplication.restcountries.data.remote

import com.example.myapplication.restcountries.data.remote.dto.CountryDto
import com.example.myapplication.restcountries.core.network.network_wrapper.ApiResult
import javax.inject.Inject

class CountriesRemoteDataSourceImpl @Inject constructor(private val service: CountriesService) :
    CountriesRemoteDataSource {
    override suspend fun getCountries(): ApiResult<List<CountryDto>> = service.getCountries()
}