package io.github.neronguyenvn.core.presentation.auth.model.error

import androidx.compose.runtime.Composable
import io.github.neronguyenvn.core.domain.auth.model.error.RegisterError
import io.github.neronguyenvn.core.presentation.model.error.asString
import kmpnerochatapp.core.presentation.generated.resources.Res
import kmpnerochatapp.core.presentation.generated.resources.error_user_already_exists
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterError.asString(): String {
    val stringRes = when (this) {
        is RegisterError.UserAlreadyExists -> Res.string.error_user_already_exists
        is RegisterError.Network -> return error.asString()
    }
    return stringResource(stringRes)
}