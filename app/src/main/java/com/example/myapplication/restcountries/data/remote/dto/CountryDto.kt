package com.example.myapplication.restcountries.data.remote.dto


import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("capital")
    val capital: List<String>,
    @SerializedName("capitalInfo")
    val capitalInfo: CapitalInfo,
    @SerializedName("flags")
    val flags: Flags,
    @SerializedName("name")
    val name: Name,
    @SerializedName("population")
    val population: Int,
    @SerializedName("startOfWeek")
    val startOfWeek: String,
    @SerializedName("timezones")
    val timezones: List<String>
)

data class CapitalInfo(
    @SerializedName("latlng")
    val latlng: List<Double>
)

data class Flags(
    @SerializedName("png")
    val png: String,
    @SerializedName("svg")
    val svg: String
)

data class Name(
    @SerializedName("common")
    val common: String,
)