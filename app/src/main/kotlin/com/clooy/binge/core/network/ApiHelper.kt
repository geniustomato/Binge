package com.clooy.binge.core.network

import retrofit2.HttpException
import retrofit2.Response

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T): ApiResult<T>()
    data class Error(val throwable: Throwable): ApiResult<Nothing>()
}

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResult<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                ApiResult.Success(body)
            } else {
                ApiResult.Error(NullPointerException("Response body is null"))
            }
        } else {
            ApiResult.Error(HttpException(response))
        }
    } catch (e: Exception) {
        ApiResult.Error(e)
    }
}

inline fun <T, R> ApiResult<T>.map(transform: (T) -> R): ApiResult<R> = when (this) {
    is ApiResult.Success -> ApiResult.Success(transform(data))
    is ApiResult.Error -> this
}

inline fun <T> ApiResult<T>.mapError(transform: (Throwable) -> Throwable): ApiResult<T> = when (this) {
    is ApiResult.Success -> this
    is ApiResult.Error -> ApiResult.Error(transform(throwable))
}
