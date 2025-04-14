package com.clooy.binge.feature.movieviewer.data.utils

import retrofit2.HttpException
import java.io.IOException

sealed class DomainResult<out T> {
    data class Success<out T>(val data: T) : DomainResult<T>()
    data class Failure(val error: DomainError) : DomainResult<Nothing>()
}

sealed class DomainError {
    data object Network : DomainError()
    data object Unauthorized : DomainError()
    data object NotFound : DomainError()
    data object Timeout : DomainError()
    data object Unknown : DomainError()
    data object NullResponseBody : DomainError()

    data class Validation(val message: String) : DomainError()
    data class Server(val code: Int, val message: String?) : DomainError()
    data class Custom(val reason: String) : DomainError()
}

fun Throwable.toDomainError(): DomainError {
    return when (this) {
        is IOException -> DomainError.Network
        is HttpException -> {
            when (this.code()) {
                401 -> DomainError.Unauthorized
                404 -> DomainError.NotFound
                408 -> DomainError.Timeout
                in 500..599 -> DomainError.Server(this.code(), this.message())
                else -> DomainError.Unknown
            }
        }

        else -> DomainError.Unknown
    }
}
