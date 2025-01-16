package com.example.myapplication.restcountries.data.local

import com.example.myapplication.restcountries.data.local.entites.CountriesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class CountriesLocalDataSourceTest : CountriesLocalDataSource {
    private val entitiesStateFlow = MutableStateFlow(listOf<CountriesEntity>())

    override suspend fun insertAllCountries(countriesEntity: List<CountriesEntity>) {
        entitiesStateFlow.update { oldValue ->
            (countriesEntity + oldValue).distinctBy(CountriesEntity::name)
        }
    }

    override fun getAllCountries(): Flow<List<CountriesEntity>> {
        return entitiesStateFlow
    }

    override fun getCountryDetails(countryName: String): Flow<CountriesEntity> {
        return entitiesStateFlow.map {
            it.first {
                it.name == countryName
            }
        }
    }
}