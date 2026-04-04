package io.github.neronguyenvn.core.domain.util

sealed interface DataError {
    enum class Remote: DataError {
        BadRequest,
        RequestTimeout,
        Unauthorized,
        Forbidden,
        NotFound,
        Conflict,
        TooManyRequests,
        NoInternet,
        PayloadTooLarge,
        ServerError,
        ServiceUnavailable,
        Serialization,
        Unknown
    }

    enum class Local: DataError {
        DiskFull,
        NotFound,
        Unknown
    }
}