package com.example.myapplication.restcountries.core.network.network_wrapper

sealed interface AppException {
    data class IOException(val cause: Throwable) : AppException
    data class RemoteDataSourceException(val body: String, val code: Int) : AppException
    data class NetworkConnectionException(val message: String = "Network Connection Error") :
        AppException
}