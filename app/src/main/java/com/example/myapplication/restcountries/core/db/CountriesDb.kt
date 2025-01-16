package com.example.myapplication.restcountries.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.restcountries.data.local.CountriesDao
import com.example.myapplication.restcountries.data.local.entites.CountriesEntity

@TypeConverters(CountriesListConvertor::class)
@Database(entities = [CountriesEntity::class], version = 1)
abstract class CountriesDb : RoomDatabase() {

    abstract fun userCommunityDao(): CountriesDao

}