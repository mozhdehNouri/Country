package com.example.myapplication.restcountries.data.di

import com.example.myapplication.restcountries.data.local.CountriesLocalDataSource
import com.example.myapplication.restcountries.data.local.CountriesLocalDataSourceImpl
import com.example.myapplication.restcountries.data.remote.CountriesRemoteDataSource
import com.example.myapplication.restcountries.data.remote.CountriesRemoteDataSourceImpl
import com.example.myapplication.restcountries.data.CountriesRepository
import com.example.myapplication.restcountries.data.CountriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface BindsCountries {


    @Binds
    fun bindsCountriesRemoteDataSource(remoteDataSourceImpl: CountriesRemoteDataSourceImpl): CountriesRemoteDataSource

    @Binds
    fun bindsCountriesLocalDataSource(localDataSourceImpl: CountriesLocalDataSourceImpl): CountriesLocalDataSource

    @Binds
    fun bindsCountriesRepository(repositoryImpl: CountriesRepositoryImpl): CountriesRepository


}