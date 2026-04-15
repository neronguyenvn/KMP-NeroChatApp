package io.github.neronguyenvn.auth.domain

sealed interface DisplayNameError {
    data object Empty : DisplayNameError
    data object Invalid : DisplayNameError
}