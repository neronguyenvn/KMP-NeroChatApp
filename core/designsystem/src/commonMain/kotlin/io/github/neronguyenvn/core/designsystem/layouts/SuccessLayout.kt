package io.github.neronguyenvn.core.designsystem.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.github.neronguyenvn.core.designsystem.components.NeroButton
import io.github.neronguyenvn.core.designsystem.components.NeroButtonStyle
import io.github.neronguyenvn.core.designsystem.components.NeroSuccessIcon
import io.github.neronguyenvn.core.designsystem.theme.NeroTheme
import io.github.neronguyenvn.core.designsystem.theme.extended

@Composable
fun NeroSuccessLayout(
    title: String,
    description: String,
    icon: @Composable () -> Unit,
    primaryButton: @Composable () -> Unit,
    secondaryButton: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        icon()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(y = -(24).dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.extended.textPrimary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.extended.textSecondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            primaryButton()
            if (secondaryButton != null) {
                Spacer(modifier = Modifier.height(8.dp))
                secondaryButton()
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun NeroSuccessLayoutPreview() {
    NeroTheme {
        Surface {
            NeroSuccessLayout(
                title = "Hello world!",
                description = "Test description",
                icon = { NeroSuccessIcon() },
                primaryButton = {
                    NeroButton(
                        text = "Log In",
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                secondaryButton = {
                    NeroButton(
                        text = "Resend verification email",
                        onClick = {},
                        style = NeroButtonStyle.Secondary,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    }
}