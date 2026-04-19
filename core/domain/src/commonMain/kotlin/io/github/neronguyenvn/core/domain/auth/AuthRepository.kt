package io.github.neronguyenvn.core.domain.auth

import arrow.core.Either
import io.github.neronguyenvn.core.domain.auth.model.error.RegisterError

interface AuthRepository {
    suspend fun register(
        email: String,
        displayName: String,
        password: String
    ): Either<RegisterError, Unit>
}