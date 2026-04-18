package io.github.neronguyenvn.core.domain.auth

import arrow.core.Either
import io.github.neronguyenvn.core.domain.model.error.DataError

interface AuthRepository {
    suspend fun register(
        email: String,
        displayName: String,
        password: String
    ): Either<DataError.Network, Unit>
}