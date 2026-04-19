package io.github.neronguyenvn.core.domain.auth.model.error

import io.github.neronguyenvn.core.domain.model.error.DataError

sealed interface RegisterError {
    data object UserAlreadyExists : RegisterError
    data class Network(val error: DataError.Network) : RegisterError
}