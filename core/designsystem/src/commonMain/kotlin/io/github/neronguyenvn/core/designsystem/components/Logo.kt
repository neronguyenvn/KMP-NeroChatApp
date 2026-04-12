package io.github.neronguyenvn.core.designsystem.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.neronguyenvn.core.designsystem.theme.extended
import kmpnerochatapp.core.designsystem.generated.resources.Res
import kmpnerochatapp.core.designsystem.generated.resources.logo_chirp
import kmpnerochatapp.core.designsystem.generated.resources.success_checkmark
import org.jetbrains.compose.resources.vectorResource

@Composable
fun NeroBrandLogo(modifier: Modifier = Modifier) {
    Icon(
        imageVector = vectorResource(Res.drawable.logo_chirp),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}

@Composable
fun NeroSuccessIcon(modifier: Modifier = Modifier) {
    Icon(
        imageVector = vectorResource(Res.drawable.success_checkmark),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.extended.success,
        modifier = modifier
    )
}