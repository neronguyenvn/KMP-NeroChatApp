package io.github.neronguyenvn.core.data.network

import arrow.core.Either
import arrow.core.raise.either
import io.github.neronguyenvn.core.domain.util.DataError
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException

suspend inline fun <reified Response> HttpClient.get(
    route: String,
    queryParams: Map<String, Any> = emptyMap(),
    noinline block: HttpRequestBuilder.() -> Unit = {}
): Either<DataError.Network, Response> = safeCall {
    get { prepareRequest(route, queryParams, block) }
}


suspend inline fun <reified Request, reified Response> HttpClient.post(
    route: String,
    body: Request,
    queryParams: Map<String, Any> = mapOf(),
    noinline block: HttpRequestBuilder.() -> Unit = {}
): Either<DataError.Network, Response> = safeCall {
    post {
        setBody(body)
        prepareRequest(route, queryParams, block)
    }
}

suspend inline fun <reified Request, reified Response : Any> HttpClient.put(
    route: String,
    body: Request,
    queryParams: Map<String, Any> = mapOf(),
    noinline block: HttpRequestBuilder.() -> Unit = {}
): Either<DataError.Network, Response> = safeCall {
    post {
        setBody(body)
        prepareRequest(route, queryParams, block)
    }
}

suspend inline fun <reified Response> HttpClient.delete(
    route: String,
    queryParams: Map<String, Any>,
    noinline block: HttpRequestBuilder.() -> Unit = {}
): Either<DataError.Network, Response> = safeCall {
    delete { prepareRequest(route, queryParams, block) }
}


fun HttpRequestBuilder.prepareRequest(
    route: String,
    queryParams: Map<String, Any> = emptyMap(),
    block: HttpRequestBuilder.() -> Unit = {}
) {
    url(constructRoute(route))
    queryParams.forEach { (key, value) ->
        parameter(key, value)
    }
    block()
}

suspend inline fun <reified T> safeCall(
    execute: suspend () -> HttpResponse
): Either<DataError.Network, T> = either {
    val response = try {
        execute()
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        raise(e.asNetworkError())
    }

    if (!response.status.isSuccess()) {
        raise(response.status.asDataError())
    }

    try {
        response.body<T>()
    } catch (_: SerializationException) {
        raise(DataError.Network.Serialization)
    } catch (_: NoTransformationFoundException) {
        raise(DataError.Network.Serialization)
    } catch (_: Exception) {
        currentCoroutineContext().ensureActive()
        raise(DataError.Network.Unknown)
    }
}

expect fun Exception.asNetworkError(): DataError.Network

fun HttpStatusCode.asDataError() = when (this) {
    HttpStatusCode.BadRequest -> DataError.Network.BadRequest
    HttpStatusCode.Unauthorized -> DataError.Network.Unauthorized
    HttpStatusCode.Forbidden -> DataError.Network.Forbidden
    HttpStatusCode.NotFound -> DataError.Network.NotFound
    HttpStatusCode.RequestTimeout -> DataError.Network.RequestTimeout
    HttpStatusCode.PayloadTooLarge -> DataError.Network.PayloadTooLarge
    HttpStatusCode.TooManyRequests -> DataError.Network.TooManyRequests
    HttpStatusCode.InternalServerError -> DataError.Network.ServerError
    HttpStatusCode.ServiceUnavailable -> DataError.Network.ServiceUnavailable
    else -> DataError.Network.Unknown
}

fun constructRoute(route: String): String {
    return when {
        route.contains(UrlConstants.BASE_URL_HTTP) -> route
        route.startsWith("/") -> "${UrlConstants.BASE_URL_HTTP}$route"
        else -> "${UrlConstants.BASE_URL_HTTP}/$route"
    }
}