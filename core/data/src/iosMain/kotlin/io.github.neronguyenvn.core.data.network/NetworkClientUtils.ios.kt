package io.github.neronguyenvn.core.data.network

import io.github.neronguyenvn.core.domain.util.DataError
import io.ktor.client.engine.darwin.DarwinHttpRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.util.network.UnresolvedAddressException
import platform.Foundation.NSURLErrorCallIsActive
import platform.Foundation.NSURLErrorCannotFindHost
import platform.Foundation.NSURLErrorDNSLookupFailed
import platform.Foundation.NSURLErrorDataNotAllowed
import platform.Foundation.NSURLErrorDomain
import platform.Foundation.NSURLErrorInternationalRoamingOff
import platform.Foundation.NSURLErrorNetworkConnectionLost
import platform.Foundation.NSURLErrorNotConnectedToInternet
import platform.Foundation.NSURLErrorResourceUnavailable
import platform.Foundation.NSURLErrorTimedOut

actual fun Exception.asNetworkError(): DataError.Network = when (this) {
    is DarwinHttpRequestException -> asNetworkError()
    is UnresolvedAddressException -> DataError.Network.NoInternet
    is HttpRequestTimeoutException -> DataError.Network.RequestTimeout
    else -> DataError.Network.Unknown
}

private fun DarwinHttpRequestException.asNetworkError(): DataError.Network {
    val nsError = origin
    if (nsError.domain != NSURLErrorDomain) {
        return DataError.Network.Unknown
    }

    return when (nsError.code) {
        NSURLErrorTimedOut -> DataError.Network.RequestTimeout

        NSURLErrorNotConnectedToInternet,
        NSURLErrorNetworkConnectionLost,
        NSURLErrorCannotFindHost,
        NSURLErrorDNSLookupFailed,
        NSURLErrorResourceUnavailable,
        NSURLErrorInternationalRoamingOff,
        NSURLErrorCallIsActive,
        NSURLErrorDataNotAllowed -> DataError.Network.NoInternet

        else -> DataError.Network.Unknown
    }
}