package com.example.myapplication.restcountries.core.db

import androidx.room.TypeConverter

class CountriesListConvertor {
    @TypeConverter
    fun fromString(listString: String): List<String> {
        if (listString.isEmpty()) return emptyList()
        return listString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }

}