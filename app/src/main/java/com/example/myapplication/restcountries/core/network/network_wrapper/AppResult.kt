package com.example.core.network_wrapper

import com.example.myapplication.restcountries.core.network.network_wrapper.AppException

sealed class AppResult<out T> {
    data class Success<out T>(val data: T) : AppResult<T>()
    data class Error<out T>(val error: AppException) : AppResult<T>()

    val isSuccess: Boolean
        get() = this is Success
    val isError: Boolean
        get() = this is Error

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[error=$error]"
        }
    }
}