package com.example.myapplication.restcountries.data.di

import com.example.myapplication.restcountries.data.local.CountriesDao
import com.example.myapplication.restcountries.core.db.CountriesDb
import com.example.myapplication.restcountries.data.remote.CountriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideCountries {

    @Provides
    @Singleton
    fun provideCountriesService(retrofit: Retrofit) : CountriesService {
        return retrofit.create(CountriesService::class.java)
    }

    @Provides
    @Singleton
    fun provideCountriesDao(roomDatabase: CountriesDb) : CountriesDao {
        return  roomDatabase.userCommunityDao()
    }
}