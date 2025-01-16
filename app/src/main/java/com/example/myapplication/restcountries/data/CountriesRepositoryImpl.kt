package com.example.myapplication.restcountries.data

import android.util.Log
import com.example.core.network_wrapper.AppResult
import com.example.myapplication.restcountries.data.local.entites.CountriesEntity
import com.example.myapplication.restcountries.data.mapper.toDomain
import com.example.myapplication.restcountries.data.mapper.toEntity
import com.example.myapplication.restcountries.domain.model.CountriesDomainEntity
import com.example.myapplication.restcountries.core.network.network_wrapper.ApiResult
import com.example.myapplication.restcountries.core.network.network_wrapper.AppException
import com.example.myapplication.restcountries.core.network.network_wrapper.toAppException
import com.example.myapplication.restcountries.core.network.utils.NetworkHandler
import com.example.myapplication.restcountries.data.local.CountriesLocalDataSource
import com.example.myapplication.restcountries.data.local.CountriesLocalDataSourceImpl
import com.example.myapplication.restcountries.data.remote.CountriesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val Europe_Content = "Europe"

class CountriesRepositoryImpl @Inject constructor(
    private val remoteDataSource: CountriesRemoteDataSource,
    private val localDataSource: CountriesLocalDataSource,
    private val networkHandler: NetworkHandler
) : CountriesRepository {
    override suspend fun getCountriesFromNetwork(): AppResult<Boolean> {
        if (!networkHandler.hasNetworkConnection()) {
            return AppResult.Error(AppException.NetworkConnectionException())
        }
        return when (val result = remoteDataSource.getCountries()) {
            is ApiResult.Error -> {
                AppResult.Error(result.error.toAppException())
            }

            is ApiResult.Success -> {
                localDataSource.insertAllCountries(result.data.toEntity())
                AppResult.Success(true)
            }
        }
    }

    override  fun showAllCountries(): Flow<List<CountriesDomainEntity>> =
        localDataSource.getAllCountries().map(List<CountriesEntity>::toDomain)

    override  fun getCountriesDetails(countriesName: String): Flow<CountriesDomainEntity> =
        localDataSource.getCountryDetails(countriesName).map(CountriesEntity::toDomain)

}