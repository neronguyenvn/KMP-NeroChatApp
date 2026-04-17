package io.github.neronguyenvn.nerochat

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.github.neronguyenvn.auth.presentation.register.RegisterRoute
import io.github.neronguyenvn.core.designsystem.theme.NeroTheme

@PreviewLightDark
@Composable
fun App() {
    NeroTheme {
        RegisterRoute()
    }
}