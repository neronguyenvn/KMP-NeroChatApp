package io.github.neronguyenvn.core.data.network

import io.github.neronguyenvn.core.domain.util.DataError
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.util.network.UnresolvedAddressException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

actual fun Exception.asNetworkError(): DataError.Network = when (this) {
    is UnknownHostException,
    is UnresolvedAddressException,
    is ConnectException -> DataError.Network.NoInternet

    is SocketTimeoutException,
    is HttpRequestTimeoutException -> DataError.Network.RequestTimeout

    else -> DataError.Network.Unknown
}