package io.github.neronguyenvn.core.domain.model.error

sealed interface DataError {
    enum class Network : DataError {
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

    enum class Local : DataError {
        DiskFull,
        NotFound,
        Unknown
    }
}