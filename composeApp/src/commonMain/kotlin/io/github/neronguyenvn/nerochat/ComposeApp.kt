package io.github.neronguyenvn.nerochat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.PreviewLightDark
import dev.zacsweers.metrox.viewmodel.LocalMetroViewModelFactory
import dev.zacsweers.metrox.viewmodel.MetroViewModelFactory
import io.github.neronguyenvn.auth.presentation.register.RegisterRoute
import io.github.neronguyenvn.core.designsystem.theme.NeroTheme

@PreviewLightDark
@Composable
fun ComposeApp(viewModelFactory: MetroViewModelFactory) {
    CompositionLocalProvider(
        value = LocalMetroViewModelFactory provides viewModelFactory
    ) {
        NeroTheme {
            RegisterRoute()
        }
    }
}