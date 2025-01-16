package com.example.myapplication.restcountries.core.network.network_wrapper

import java.io.IOException

sealed interface ApiError {
    data class HttpError(val code: Int, val body: String) : ApiError
    data class IOError(val exception: IOException) : ApiError
    data class UnknownApiError(val throwable: Throwable) : ApiError
    data object EmptyBodyError : ApiError

    fun getException(): Throwable {
        return when (this) {
            EmptyBodyError -> EmptyBodyException()
            is HttpError -> RemoteDataSourceException(code)
            is IOError -> exception
            is UnknownApiError -> throwable
        }
    }
}

fun ApiError.toAppException(): AppException =
    when (this) {
        is ApiError.HttpError -> AppException.RemoteDataSourceException(this.body, this.code)
        is ApiError.IOError -> AppException.IOException(exception)
        is ApiError.UnknownApiError -> AppException.IOException(throwable)
        is ApiError.EmptyBodyError -> AppException.IOException(IllegalStateException("empty body"))
    }