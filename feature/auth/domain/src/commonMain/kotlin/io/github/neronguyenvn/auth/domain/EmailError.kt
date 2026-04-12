package io.github.neronguyenvn.auth.domain

sealed interface EmailError {
    data object Invalid : EmailError
}