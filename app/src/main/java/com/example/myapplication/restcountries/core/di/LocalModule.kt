package com.example.myapplication.restcountries.core.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.restcountries.core.db.CountriesDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideCountriesRoomDataBase(@ApplicationContext context: Context): CountriesDb {
        return Room
            .databaseBuilder(
                context,
                CountriesDb::class.java,
                "countriesDb.db"
            )
            .build()
    }
}