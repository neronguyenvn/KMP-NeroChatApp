package io.github.neronguyenvn.auth.domain.model.error

sealed interface DisplayNameError {
    data object Empty : DisplayNameError
    data object Invalid : DisplayNameError
}