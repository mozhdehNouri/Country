package com.example.myapplication.restcountries.domain.model

data class CountriesDomainEntity(
    val capital: List<String>,
    val capitalInfo: List<String>,
    val flags: String,
    val name: String,
    val population: Int,
    val startOfWeek: String,
    val timezones: List<String>
)