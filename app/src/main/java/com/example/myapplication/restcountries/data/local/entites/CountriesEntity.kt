package com.example.myapplication.restcountries.data.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountriesEntity(
    val capital: List<String>,
    val capitalInfo: List<String>,
    val flags: String,
    @PrimaryKey
    val name: String,
    val population: Int,
    val startOfWeek: String,
    val timezones: List<String>
)