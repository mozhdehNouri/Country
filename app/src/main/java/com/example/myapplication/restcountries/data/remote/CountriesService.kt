package com.example.myapplication.restcountries.data.remote

import com.example.myapplication.restcountries.data.remote.dto.CountryDto
import com.example.myapplication.restcountries.core.network.network_wrapper.ApiResult
import retrofit2.http.GET
import retrofit2.http.Headers

interface CountriesService {

    @GET("v3.1/region/europe")
    suspend fun getCountries(): ApiResult<List<CountryDto>>

}
