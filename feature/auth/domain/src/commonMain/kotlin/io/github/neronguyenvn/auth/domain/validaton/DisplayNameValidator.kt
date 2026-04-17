package io.github.neronguyenvn.auth.domain.validaton

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import io.github.neronguyenvn.auth.domain.model.error.DisplayNameError

object DisplayNameValidator {

    fun validate(displayName: String): Either<DisplayNameError, String> = either {
        val trimmedDisplayName = displayName.trim()
        ensure(trimmedDisplayName.isNotEmpty()) {
            DisplayNameError.Empty
        }
        ensure(trimmedDisplayName.all { it.isLetterOrDigit() || it == ' ' }) {
            DisplayNameError.Invalid
        }
        trimmedDisplayName
    }
}