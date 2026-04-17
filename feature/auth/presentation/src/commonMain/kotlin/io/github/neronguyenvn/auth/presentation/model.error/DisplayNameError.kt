package io.github.neronguyenvn.auth.presentation.model.error

import androidx.compose.runtime.Composable
import io.github.neronguyenvn.auth.domain.model.error.DisplayNameError
import kmpnerochatapp.feature.auth.presentation.generated.resources.Res
import kmpnerochatapp.feature.auth.presentation.generated.resources.error_display_name_empty
import kmpnerochatapp.feature.auth.presentation.generated.resources.error_display_name_invalid
import org.jetbrains.compose.resources.stringResource

@Composable
fun DisplayNameError.asString(): String {
    val stringRes = when (this) {
        DisplayNameError.Empty -> Res.string.error_display_name_empty
        DisplayNameError.Invalid -> Res.string.error_display_name_invalid
    }
    return stringResource(stringRes)
}