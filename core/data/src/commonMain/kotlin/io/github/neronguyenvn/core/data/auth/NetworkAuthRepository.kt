package io.github.neronguyenvn.core.data.auth

import arrow.core.Either
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import io.github.neronguyenvn.core.data.auth.model.register.RegisterRequest
import io.github.neronguyenvn.core.data.network.post
import io.github.neronguyenvn.core.domain.auth.AuthRepository
import io.github.neronguyenvn.core.domain.auth.model.error.RegisterError
import io.github.neronguyenvn.core.domain.model.error.DataError
import io.ktor.client.HttpClient

@ContributesBinding(AppScope::class)
class NetworkAuthRepository(
    private val httpClient: HttpClient
) : AuthRepository {

    override suspend fun register(
        email: String,
        displayName: String,
        password: String
    ): Either<RegisterError, Unit> = httpClient
        .post<RegisterRequest, Unit>(
            route = "/auth/register",
            body = RegisterRequest(
                email = email,
                displayName = displayName,
                password = password
            )
        )
        .mapLeft { error ->
            if (error == DataError.Network.Conflict) RegisterError.UserAlreadyExists
            else RegisterError.Network(error)
        }
}