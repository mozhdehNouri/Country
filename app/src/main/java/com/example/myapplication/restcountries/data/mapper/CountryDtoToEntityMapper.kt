package com.example.myapplication.restcountries.data.mapper

import com.example.myapplication.restcountries.data.remote.dto.CountryDto
import com.example.myapplication.restcountries.data.local.entites.CountriesEntity

fun List<CountryDto>.toEntity() = map {
    CountriesEntity(
        capital = it.capital, capitalInfo = it.capitalInfo.latlng.map { it.toString() }, flags = it.flags.png, name = it.name.common,
        population = it.population, startOfWeek = it.startOfWeek, timezones = it.timezones)
}