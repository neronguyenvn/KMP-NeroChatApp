package io.github.neronguyenvn.core.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.github.neronguyenvn.core.designsystem.theme.NeroTheme
import io.github.neronguyenvn.core.designsystem.theme.extended

enum class NeroButtonStyle {
    Primary,
    Secondary,
    DestructivePrimary,
    DestructiveSecondary,
    Text
}

@Composable
fun NeroButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: NeroButtonStyle = NeroButtonStyle.Primary,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    val colors = when (style) {
        NeroButtonStyle.Primary -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.extended.disabledFill,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled
        )

        NeroButtonStyle.Secondary -> ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.extended.textSecondary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled
        )

        NeroButtonStyle.DestructivePrimary -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError,
            disabledContainerColor = MaterialTheme.colorScheme.extended.disabledFill,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled
        )

        NeroButtonStyle.DestructiveSecondary -> ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.error,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled
        )

        NeroButtonStyle.Text -> ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.tertiary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.extended.textDisabled
        )
    }

    val defaultBorderStroke = BorderStroke(
        width = 1.dp,
        color = MaterialTheme.colorScheme.extended.disabledOutline
    )

    val border = when (style) {
        NeroButtonStyle.Primary if !enabled -> defaultBorderStroke
        NeroButtonStyle.Secondary -> defaultBorderStroke
        NeroButtonStyle.DestructivePrimary if !enabled -> defaultBorderStroke

        NeroButtonStyle.DestructiveSecondary -> {
            val borderColor =
                if (enabled) MaterialTheme.colorScheme.extended.destructiveSecondaryOutline
                else MaterialTheme.colorScheme.extended.disabledOutline

            BorderStroke(
                width = 1.dp,
                color = borderColor
            )
        }

        else -> null
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = colors,
        border = border
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 1.5.dp,
                    color = Color.Black
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.alpha(if (isLoading) 0f else 1f)
            ) {
                leadingIcon?.invoke()
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun NeroButtonPreview() {
    NeroTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            NeroButtonStyle.entries.forEach {
                NeroButton(
                    text = "Hello world!",
                    onClick = {},
                    style = it
                )
            }
        }
    }
}