package com.example.myapplication.restcountries.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: RestCountries)

enum class RestCountries {
    Default,
    IO
}
