package com.example.myapplication.restcountries.data.local

import com.example.myapplication.restcountries.data.local.entites.CountriesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountriesLocalDataSourceImpl @Inject constructor(private val dao: CountriesDao):
    CountriesLocalDataSource {
    override suspend fun insertAllCountries(countriesEntity: List<CountriesEntity>)  =dao.insertAllCountries(countriesEntity)

    override fun getAllCountries(): Flow<List<CountriesEntity>> = dao.getAllCountries()

    override fun getCountryDetails(countryName: String): Flow<CountriesEntity>  =dao.getCountryDetails(countryName)
}