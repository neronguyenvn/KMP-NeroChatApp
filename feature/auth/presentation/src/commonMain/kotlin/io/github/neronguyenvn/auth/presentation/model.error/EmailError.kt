package io.github.neronguyenvn.auth.presentation.model.error

import androidx.compose.runtime.Composable
import io.github.neronguyenvn.auth.domain.model.error.EmailError
import kmpnerochatapp.feature.auth.presentation.generated.resources.Res
import kmpnerochatapp.feature.auth.presentation.generated.resources.error_email_empty
import kmpnerochatapp.feature.auth.presentation.generated.resources.error_email_invalid
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmailError.asString(): String {
    val stringRes = when (this) {
        EmailError.Empty -> Res.string.error_email_empty
        EmailError.Invalid -> Res.string.error_email_invalid
    }
    return stringResource(stringRes)
}