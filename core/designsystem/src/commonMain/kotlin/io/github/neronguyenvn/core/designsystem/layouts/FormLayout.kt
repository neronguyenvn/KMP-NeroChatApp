package io.github.neronguyenvn.core.designsystem.layouts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import io.github.neronguyenvn.core.designsystem.components.NeroBrandLogo
import io.github.neronguyenvn.core.designsystem.theme.NeroTheme
import io.github.neronguyenvn.core.designsystem.theme.extended
import io.github.neronguyenvn.core.designsystem.util.WindowConfig
import io.github.neronguyenvn.core.designsystem.util.currentWindowConfig

@Composable
fun NeroFormLayout(
    headerText: String,
    modifier: Modifier = Modifier,
    errorText: String? = null,
    logo: @Composable () -> Unit,
    formContent: @Composable ColumnScope.() -> Unit,
) {
    when (currentWindowConfig()) {
        WindowConfig.MobilePortrait -> {
            Scaffold { innerPadding ->
                NeroSurface(
                    modifier = modifier.padding(innerPadding),
                    header = {
                        Spacer(modifier = Modifier.height(32.dp))
                        logo()
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    AuthHeaderSection(
                        headerText = headerText,
                        errorText = errorText
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    formContent()
                }
            }
        }

        WindowConfig.MobileLandscape -> {
            Scaffold { innerPadding ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(top = 24.dp)
                        .padding(horizontal = 24.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        logo()
                        Spacer(modifier = Modifier.height(24.dp))
                        AuthHeaderSection(
                            headerText = headerText,
                            errorText = errorText
                        )
                    }
                    NeroSurface(modifier = Modifier.weight(1f)) {
                        formContent()
                    }
                }
            }
        }

        else -> {
            Scaffold { innerPadding ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    logo()
                    Spacer(modifier = Modifier.height(32.dp))
                    Surface(
                        modifier = Modifier.clip(RoundedCornerShape(16.dp))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .width(480.dp)
                                .padding(24.dp)
                        ) {
                            AuthHeaderSection(
                                headerText = headerText,
                                errorText = errorText
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            formContent()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.AuthHeaderSection(
    headerText: String,
    errorText: String? = null,
) {
    val windowConfig = currentWindowConfig()

    val headerColor = if (windowConfig == WindowConfig.MobileLandscape) {
        MaterialTheme.colorScheme.onBackground
    } else {
        MaterialTheme.colorScheme.extended.textPrimary
    }

    Text(
        text = headerText,
        style = MaterialTheme.typography.titleLarge,
        color = headerColor,
    )

    AnimatedVisibility(visible = !errorText.isNullOrBlank()) {
        Text(
            text = errorText.orEmpty(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.error,
        )
    }
}

@PreviewLightDark
@PreviewScreenSizes
@Composable
private fun NeroFormLayoutPreview() {
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