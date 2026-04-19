package io.github.neronguyenvn.core.data.auth.model.register

import kotlinx.serialization.Serializable

@Serializable
internal data class RegisterRequest(
    val email: String,
    val displayName: String,
    val password: String
)