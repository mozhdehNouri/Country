package com.example.myapplication.restcountries.core.network.utils

sealed class NetworkStatus {
    data object Available : NetworkStatus()
    data object Unavailable : NetworkStatus()
}