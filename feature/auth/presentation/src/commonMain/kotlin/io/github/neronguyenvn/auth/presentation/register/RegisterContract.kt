package io.github.neronguyenvn.auth.presentation.register

import io.github.neronguyenvn.auth.domain.model.error.DisplayNameError
import io.github.neronguyenvn.auth.domain.model.error.EmailError
import io.github.neronguyenvn.core.domain.auth.model.error.PasswordError

data class RegisterUiState(
    val isRegistering: Boolean = false,
    val emailError: EmailError? = null,
    val displayNameError: DisplayNameError? = null,
    val passwordError: PasswordError? = null
)

sealed interface RegisterIntent {
    data object SubmitRegistration : RegisterIntent
    data object NavigateToLogin : RegisterIntent
}