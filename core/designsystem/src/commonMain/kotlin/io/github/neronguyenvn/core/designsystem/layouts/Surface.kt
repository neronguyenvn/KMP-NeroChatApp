package io.github.neronguyenvn.core.designsystem.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.github.neronguyenvn.core.designsystem.theme.NeroTheme
import kmpnerochatapp.core.designsystem.generated.resources.Res
import kmpnerochatapp.core.designsystem.generated.resources.logo_chirp
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun NeroSurface(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (header != null) {
                header()
            }

            Surface(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    content()
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun NeroSurfacePreview() {
    NeroTheme {
        NeroSurface(
            header = {
                Icon(
                    imageVector = vectorResource(Res.drawable.logo_chirp),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(vertical = 32.dp)
                )
            },
            content = {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Welcome to Nero Chat!",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        )
    }
}