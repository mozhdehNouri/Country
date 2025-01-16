package com.example.myapplication.restcountries.core.network.network_wrapper

class RemoteDataSourceException(
    val code: Int
) : Exception("Remote error: code=$code}")