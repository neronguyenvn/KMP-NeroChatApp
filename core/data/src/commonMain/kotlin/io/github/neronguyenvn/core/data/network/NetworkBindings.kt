package io.github.neronguyenvn.core.data.network

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@BindingContainer
@ContributesTo(AppScope::class)
object NetworkBindings {

    @Provides
    fun provideHttpClient(engine: HttpClientEngine): HttpClient {
        val requestTimeoutMillis = 15_000L
        val json = Json {
            ignoreUnknownKeys = true
        }

        return HttpClient(engine) {
            install(WebSockets)
            install(Logging)

            install(HttpTimeout) {
                this.requestTimeoutMillis = requestTimeoutMillis
            }

            install(ContentNegotiation) {
                json(json = json)
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }
}