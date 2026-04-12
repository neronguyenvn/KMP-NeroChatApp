package io.github.neronguyenvn.core.domain.validation

sealed interface PasswordError {
    data object TooShort : PasswordError
    data object NoDigit : PasswordError
    data object NoUppercase : PasswordError
}