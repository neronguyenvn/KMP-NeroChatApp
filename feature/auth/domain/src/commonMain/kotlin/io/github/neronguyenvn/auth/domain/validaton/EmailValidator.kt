package io.github.neronguyenvn.auth.domain.validaton

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import io.github.neronguyenvn.auth.domain.model.error.EmailError

object EmailValidator {

    private val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex()

    fun validate(email: String): Either<EmailError, String> = either {
        val trimmedEmail = email.trim()
        ensure(trimmedEmail.isNotEmpty()) { EmailError.Empty }
        ensure(emailRegex.matches(trimmedEmail)) { EmailError.Invalid }
        trimmedEmail
    }
}