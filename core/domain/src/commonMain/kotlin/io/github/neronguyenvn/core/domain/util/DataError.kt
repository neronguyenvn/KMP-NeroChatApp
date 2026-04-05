package io.github.neronguyenvn.core.domain.util

sealed interface DataError {
    enum class Network : DataError {
        BadRequest,
        RequestTimeout,
        Unauthorized,
        Forbidden,
        NotFound,
        TooManyRequests,
        NoInternet,
        PayloadTooLarge,
        ServerError,
        ServiceUnavailable,
        Serialization,
        Unknown
    }

    enum class Local : DataError {
        DiskFull,
        NotFound,
        Unknown
    }
}