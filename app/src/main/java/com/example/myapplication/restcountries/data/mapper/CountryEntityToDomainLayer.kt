package com.example.myapplication.restcountries.data.mapper

import com.example.myapplication.restcountries.data.remote.dto.CountryDto
import com.example.myapplication.restcountries.data.local.entites.CountriesEntity
import com.example.myapplication.restcountries.domain.model.CountriesDomainEntity

fun CountriesEntity.toDomain() = CountriesDomainEntity(
    capital = capital, capitalInfo = capitalInfo, flags = flags, name = name,
    population = population, startOfWeek = startOfWeek, timezones = timezones)


fun List<CountriesEntity>.toDomain() = map {
    CountriesDomainEntity(
        capital = it.capital, capitalInfo = it.capitalInfo, flags = it.flags, name = it.name,
        population = it.population, startOfWeek = it.startOfWeek, timezones = it.timezones)
}