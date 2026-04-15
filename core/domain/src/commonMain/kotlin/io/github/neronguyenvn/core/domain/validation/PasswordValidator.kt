package io.github.neronguyenvn.core.domain.validation

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure

object PasswordValidator {

    private const val MIN_PASSWORD_LENGTH = 8

    fun validate(password: String): Either<PasswordError, String> = either {
        val trimmedPassword = password.trim()
        ensure(trimmedPassword.length >= MIN_PASSWORD_LENGTH) { PasswordError.TooShort }
        ensure(trimmedPassword.any { it.isDigit() }) { PasswordError.NoDigit }
        ensure(trimmedPassword.any { it.isUpperCase() }) { PasswordError.NoUppercase }
        ensure(trimmedPassword.none { it.isWhitespace() }) { PasswordError.HasWhiteSpace }
        trimmedPassword
    }
}