package com.example.myapplication.restcountries.data.remote

import com.example.myapplication.restcountries.data.remote.dto.CountryDto
import com.example.myapplication.restcountries.core.network.network_wrapper.ApiResult

interface CountriesRemoteDataSource {
    suspend fun getCountries():ApiResult<List<CountryDto>>
}
