package io.github.neronguyenvn.auth.domain.model.error

sealed interface EmailError {
    data object Empty : EmailError
    data object Invalid : EmailError
}