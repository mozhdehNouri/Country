package com.example.myapplication.restcountries.core.network.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Checks if a network connection exists.
 */
class NetworkHandler @Inject constructor(
    @ApplicationContext val context: Context
) {
    fun hasNetworkConnection(): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> {
                    // on some devices (Samsung Note 8) when vpn is connected  none of the other
                    // transports are set, so we check if internet is validated means there is some internet connection!
                    actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                            actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                }
            }
        } else {
            @Suppress("DEPRECATION")
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }

}