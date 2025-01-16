package com.example.myapplication.restcountries.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class CountriesUiModel(
    val capital: List<String> = emptyList(),
    val capitalInfo: List<String> = emptyList(),
    val flags: String ="",
    val name: String =" ",
    val population: Int = 0 ,
    val startOfWeek: String= "",
    val timezones: List<String> = emptyList()
)