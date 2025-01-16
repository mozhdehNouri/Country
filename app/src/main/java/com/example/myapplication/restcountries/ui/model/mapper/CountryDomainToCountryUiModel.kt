package com.example.myapplication.restcountries.ui.model.mapper

import com.example.myapplication.restcountries.domain.model.CountriesDomainEntity
import com.example.myapplication.restcountries.ui.model.CountriesUiModel


fun CountriesDomainEntity.toUi() = CountriesUiModel(
    capital = capital, capitalInfo = capitalInfo, flags = flags, name = name,
    population = population, startOfWeek = startOfWeek, timezones = timezones)


fun List<CountriesDomainEntity>.toUi() = map {
    CountriesUiModel(
        capital = it.capital, capitalInfo = it.capitalInfo, flags = it.flags, name = it.name,
        population = it.population, startOfWeek = it.startOfWeek, timezones = it.timezones)
}