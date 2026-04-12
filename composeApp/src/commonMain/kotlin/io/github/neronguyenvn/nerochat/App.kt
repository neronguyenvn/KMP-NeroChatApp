package io.github.neronguyenvn.nerochat

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.github.neronguyenvn.core.designsystem.components.NeroBrandLogo
import io.github.neronguyenvn.core.designsystem.layouts.NeroFormLayout
import io.github.neronguyenvn.core.designsystem.theme.NeroTheme

@PreviewLightDark
@Composable
fun App() {
    NeroTheme {
        NeroFormLayout(
            headerText = "Welcome to Nero!",
            errorText = "Login failed!",
            logo = { NeroBrandLogo() },
            formContent = {
                Text(
                    text = "Sample form title",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Sample form title 2",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        )
    }
}