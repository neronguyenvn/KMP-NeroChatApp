package io.github.neronguyenvn.auth.presentation.register

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.neronguyenvn.auth.domain.validaton.DisplayNameValidator
import io.github.neronguyenvn.auth.domain.validaton.EmailValidator
import io.github.neronguyenvn.core.domain.auth.validation.PasswordValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RegisterUiState()
    )

    val emailState = TextFieldState()
    val displayNameState = TextFieldState()
    val passwordState = TextFieldState()

    fun onIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.SubmitRegistration -> validateInputs()
            else -> Unit
        }
    }

    private fun validateInputs(): Boolean {
        resetInputErrors()

        val emailResult = EmailValidator.validate(emailState.text.toString())
        val nameResult = DisplayNameValidator.validate(displayNameState.text.toString())
        val passResult = PasswordValidator.validate(passwordState.text.toString())

        _uiState.update {
            it.copy(
                emailError = emailResult.leftOrNull(),
                displayNameError = nameResult.leftOrNull(),
                passwordError = passResult.leftOrNull()
            )
        }

        return emailResult.isRight() && nameResult.isRight() && passResult.isRight()
    }

    private fun resetInputErrors() {
        _uiState.update {
            it.copy(
                emailError = null,
                displayNameError = null,
                passwordError = null
            )
        }
    }
}