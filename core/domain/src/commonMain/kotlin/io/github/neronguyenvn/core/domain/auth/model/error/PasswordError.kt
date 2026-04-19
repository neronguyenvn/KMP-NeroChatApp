package io.github.neronguyenvn.core.domain.auth.model.error

sealed interface PasswordError {
    data object TooShort : PasswordError
    data object NoDigit : PasswordError
    data object NoUppercase : PasswordError
    data object HasWhiteSpace : PasswordError
}