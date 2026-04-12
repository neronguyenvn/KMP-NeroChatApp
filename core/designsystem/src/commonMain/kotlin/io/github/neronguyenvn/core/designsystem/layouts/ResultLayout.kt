package io.github.neronguyenvn.core.designsystem.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import io.github.neronguyenvn.core.designsystem.util.WindowConfig
import io.github.neronguyenvn.core.designsystem.util.currentWindowConfig

@Composable
fun NeroResultLayout(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val windowConfig = currentWindowConfig()

    Scaffold(modifier = modifier) { innerPadding ->
        if (windowConfig == WindowConfig.MobilePortrait) {
            NeroSurface(
                header = {
                    Spacer(modifier = Modifier.height(32.dp))
                    NeroBrandLogo()
                    Spacer(modifier = Modifier.height(32.dp))
                },
                content = content,
                modifier = Modifier.padding(innerPadding),
            )
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (windowConfig != WindowConfig.MobileLandscape) {
                    NeroBrandLogo()
                    Spacer(modifier = Modifier.height(32.dp))
                }

                Surface(
                    modifier = Modifier.clip(RoundedCornerShape(16.dp))
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .widthIn(max = 480.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        content()
                    }
                }
            }
        }
    }
}


@PreviewLightDark
@PreviewScreenSizes
@Composable
fun NeroResultLayoutPreview() {
    NeroTheme {
        NeroResultLayout(
            modifier = Modifier.fillMaxSize(),
            content = {
                Column(modifier = Modifier.padding(vertical = 24.dp)) {
                    Text(
                        text = "Registration successful!",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        )
    }
}