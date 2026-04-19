package io.github.neronguyenvn.core.presentation.auth.model.error

import androidx.compose.runtime.Composable
import io.github.neronguyenvn.core.domain.auth.model.error.PasswordError
import kmpnerochatapp.core.presentation.generated.resources.Res
import kmpnerochatapp.core.presentation.generated.resources.error_password_has_whitespace
import kmpnerochatapp.core.presentation.generated.resources.error_password_no_digit
import kmpnerochatapp.core.presentation.generated.resources.error_password_no_uppercase
import kmpnerochatapp.core.presentation.generated.resources.error_password_too_short
import org.jetbrains.compose.resources.stringResource

@Composable
fun PasswordError.asString(): String {
    val stringRes = when (this) {
        PasswordError.HasWhiteSpace -> Res.string.error_password_has_whitespace
        PasswordError.NoDigit -> Res.string.error_password_no_digit
        PasswordError.NoUppercase -> Res.string.error_password_no_uppercase
        PasswordError.TooShort -> Res.string.error_password_too_short
    }
    return stringResource(stringRes)
}