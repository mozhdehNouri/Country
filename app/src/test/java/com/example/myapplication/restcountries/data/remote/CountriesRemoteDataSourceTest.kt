package com.example.myapplication.restcountries.data.remote

import com.example.myapplication.restcountries.core.network.network_wrapper.ApiResult
import com.example.myapplication.restcountries.data.remote.dto.CapitalInfo
import com.example.myapplication.restcountries.data.remote.dto.CountryDto
import com.example.myapplication.restcountries.data.remote.dto.Flags
import com.example.myapplication.restcountries.data.remote.dto.Name

class CountriesRemoteDataSourceTest : CountriesRemoteDataSource {
    override suspend fun getCountries(): ApiResult<List<CountryDto>> {
        return ApiResult.Success(
            listOf(
                CountryDto(
                    capital = listOf("Berlin"),
                    capitalInfo = CapitalInfo(listOf(10.2, 45.2)),
                    flags = Flags(
                        "https://upload.wikimedia.org/wikipedia/en/b/ba/Flag_of_Germany.svg",
                        "https://upload.wikimedia.org/wikipedia/en/b/ba/Flag_of_Germany.svg"
                    ),
                    name = Name("Germany"),
                    population = 83783942,
                    startOfWeek = "Monday",
                    timezones = listOf("UTC+1", "UTC+2 (during DST)")
                )
            )
        )

    }
}