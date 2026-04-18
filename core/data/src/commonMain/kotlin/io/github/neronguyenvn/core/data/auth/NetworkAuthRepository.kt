package io.github.neronguyenvn.core.data.auth

import arrow.core.Either
import io.github.neronguyenvn.core.data.auth.model.RegisterRequest
import io.github.neronguyenvn.core.data.network.post
import io.github.neronguyenvn.core.domain.auth.AuthRepository
import io.github.neronguyenvn.core.domain.model.error.DataError
import io.ktor.client.HttpClient

internal class NetworkAuthRepository(
    private val httpClient: HttpClient
) : AuthRepository {

    override suspend fun register(
        email: String,
        displayName: String,
        password: String
    ): Either<DataError.Network, Unit> {
        return httpClient.post(
            route = "/auth/register",
            body = RegisterRequest(
                email = email,
                displayName = displayName,
                password = password
            )
        )
    }
}