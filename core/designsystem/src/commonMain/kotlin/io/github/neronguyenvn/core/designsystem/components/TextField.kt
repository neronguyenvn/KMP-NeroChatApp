package io.github.neronguyenvn.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.github.neronguyenvn.core.designsystem.components.internal.TextFieldLayout
import io.github.neronguyenvn.core.designsystem.theme.NeroTheme
import io.github.neronguyenvn.core.designsystem.theme.extended
import kmpnerochatapp.core.designsystem.generated.resources.Res
import kmpnerochatapp.core.designsystem.generated.resources.eye_icon
import kmpnerochatapp.core.designsystem.generated.resources.eye_off_icon
import org.jetbrains.compose.resources.vectorResource

@Composable
fun NeroTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    title: String? = null,
    placeholder: String? = null,
    supportingText: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true,
    isError: Boolean = false,
    onFocusChanged: ((Boolean) -> Unit)? = null,
) {
    TextFieldLayout(
        title = title,
        supportingText = supportingText,
        enabled = enabled,
        isError = isError,
        onFocusChanged = onFocusChanged,
        modifier = modifier
    ) { textFieldModifier, interactionSource ->
        BasicTextField(
            state = state,
            enabled = enabled,
            lineLimits = TextFieldLineLimits.SingleLine,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color =
                    if (enabled) MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.extended.textPlaceholder
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
            interactionSource = interactionSource,
            modifier = textFieldModifier,
            decorator = { innerBox ->
                Box {
                    if (state.text.isEmpty() && placeholder != null) {
                        Text(
                            text = placeholder,
                            color = MaterialTheme.colorScheme.extended.textPlaceholder,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerBox()
                }
            }
        )
    }
}


@Composable
fun NeroPasswordTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    title: String? = null,
    placeholder: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    onFocusChanged: (Boolean) -> Unit = {},
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    TextFieldLayout(
        title = title,
        isError = isError,
        supportingText = supportingText,
        onFocusChanged = onFocusChanged,
        modifier = modifier
    ) { textFieldModifier, interactionSource ->
        BasicSecureTextField(
            state = state,
            textObfuscationMode =
                if (isPasswordVisible) TextObfuscationMode.Visible
                else TextObfuscationMode.Hidden,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            interactionSource = interactionSource,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
            modifier = textFieldModifier,
            decorator = { innerBox ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (state.text.isEmpty() && placeholder != null) {
                            Text(
                                text = placeholder,
                                color = MaterialTheme.colorScheme.extended.textPlaceholder,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        innerBox()
                    }
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector =
                                if (isPasswordVisible) vectorResource(Res.drawable.eye_off_icon)
                                else vectorResource(Res.drawable.eye_icon),
                            tint = MaterialTheme.colorScheme.extended.textDisabled,
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}

@PreviewLightDark
@Composable
fun NeroTextFieldEmptyPreview() {
    NeroTheme {
        NeroTextField(
            state = rememberTextFieldState(),
            modifier = Modifier.width(300.dp),
            placeholder = "test@test.com",
            title = "Email",
            supportingText = "Please enter your email",
        )
    }
}

@PreviewLightDark
@Composable
fun NeroTextFieldFilledPreview() {
    NeroTheme {
        NeroTextField(
            state = rememberTextFieldState(
                initialText = "test@test.com"
            ),
            modifier = Modifier.width(300.dp),
            placeholder = "test@test.com",
            title = "Email",
            supportingText = "Please enter your email",
        )
    }
}

@PreviewLightDark
@Composable
fun NeroTextFieldDisabledPreview() {
    NeroTheme {
        NeroTextField(
            state = rememberTextFieldState(),
            modifier = Modifier.width(300.dp),
            placeholder = "test@test.com",
            title = "Email",
            supportingText = "Please enter your email",
            enabled = false
        )
    }
}

@PreviewLightDark
@Composable
fun NeroTextFieldErrorPreview() {
    NeroTheme {
        NeroTextField(
            state = rememberTextFieldState(),
            modifier = Modifier.width(300.dp),
            placeholder = "test@test.com",
            title = "Email",
            supportingText = "This is not a valid email",
            isError = true,
        )
    }
}

@PreviewLightDark
@Composable
fun NeroPasswordTextFieldEmptyPreview() {
    NeroTheme {
        NeroPasswordTextField(
            state = rememberTextFieldState(),
            modifier = Modifier.width(300.dp),
            placeholder = "Password",
            title = "Password",
            supportingText = "Use 9+ characters, at least one digit and one uppercase letter",
        )
    }
}

@PreviewLightDark
@Composable
fun NeroPasswordTextFieldFilledPreview() {
    NeroTheme {
        NeroPasswordTextField(
            state = rememberTextFieldState("password123"),
            modifier = Modifier.width(300.dp),
            placeholder = "Password",
            title = "Password",
            supportingText = "Use 9+ characters, at least one digit and one uppercase letter",
        )
    }
}

@PreviewLightDark
@Composable
fun NeroPasswordTextFieldErrorPreview() {
    NeroTheme {
        NeroPasswordTextField(
            state = rememberTextFieldState("password123"),
            modifier = Modifier.width(300.dp),
            placeholder = "Password",
            title = "Password",
            supportingText = "Doesn't contain an uppercase character",
            isError = true,
        )
    }
}