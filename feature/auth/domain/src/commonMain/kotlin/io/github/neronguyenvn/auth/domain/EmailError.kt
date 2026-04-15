package io.github.neronguyenvn.auth.domain

sealed interface EmailError {
    data object Empty : EmailError
    data object Invalid : EmailError
}