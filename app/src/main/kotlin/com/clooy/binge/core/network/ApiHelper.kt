package com.clooy.binge.core.network

import com.clooy.binge.feature.movieviewer.data.utils.DomainError
import com.clooy.binge.feature.movieviewer.data.utils.DomainResult
import com.clooy.binge.feature.movieviewer.data.utils.toDomainError
import retrofit2.HttpException
import retrofit2.Response

// TODO Revisit this when there's more time
suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): DomainResult<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                DomainResult.Success(body)
            } else {
                DomainResult.Failure(DomainError.NullResponseBody)
            }
        } else {
            DomainResult.Failure(HttpException(response).toDomainError())
        }
    } catch (e: Exception) {
        DomainResult.Failure(e.toDomainError())
    }
}

inline fun <T, R> DomainResult<T>.onSuccess(transform: (T) -> R): DomainResult<R> = when (this) {
    is DomainResult.Success -> DomainResult.Success(transform(data))
    is DomainResult.Failure -> this
}
