package com.example.myapplication.restcountries.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO

@Module
@InstallIn(SingletonComponent::class)
internal object CoroutineModule {
    @Provides
    @Dispatcher(RestCountries.IO)
    fun provideIODispatcher(): CoroutineDispatcher = IO

    @Dispatcher(RestCountries.Default)
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Default
}