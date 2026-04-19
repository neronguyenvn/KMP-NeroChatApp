package io.github.neronguyenvn.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.neronguyenvn.auth.presentation.model.register.error.asString
import io.github.neronguyenvn.core.designsystem.components.NeroBrandLogo
import io.github.neronguyenvn.core.designsystem.components.NeroButton
import io.github.neronguyenvn.core.designsystem.components.NeroButtonStyle
import io.github.neronguyenvn.core.designsystem.components.NeroPasswordTextField
import io.github.neronguyenvn.core.designsystem.components.NeroTextField
import io.github.neronguyenvn.core.designsystem.layouts.NeroFormLayout
import io.github.neronguyenvn.core.designsystem.theme.NeroTheme
import io.github.neronguyenvn.core.presentation.auth.model.error.asString
import kmpnerochatapp.feature.auth.presentation.generated.resources.Res
import kmpnerochatapp.feature.auth.presentation.generated.resources.display_name_placeholder
import kmpnerochatapp.feature.auth.presentation.generated.resources.email
import kmpnerochatapp.feature.auth.presentation.generated.resources.email_placeholder
import kmpnerochatapp.feature.auth.presentation.generated.resources.login
import kmpnerochatapp.feature.auth.presentation.generated.resources.password
import kmpnerochatapp.feature.auth.presentation.generated.resources.password_hint
import kmpnerochatapp.feature.auth.presentation.generated.resources.register
import kmpnerochatapp.feature.auth.presentation.generated.resources.welcome_to_chat
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterRoute(viewModel: RegisterViewModel = viewModel { RegisterViewModel() }) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    RegisterScreen(
        uiState = uiState,
        emailState = viewModel.emailState,
        displayNameState = viewModel.displayNameState,
        passwordState = viewModel.passwordState,
        onIntent = viewModel::onIntent
    )
}

@Composable
internal fun RegisterScreen(
    uiState: RegisterUiState,
    emailState: TextFieldState,
    displayNameState: TextFieldState,
    passwordState: TextFieldState,
    onIntent: (RegisterIntent) -> Unit,
) {
    NeroFormLayout(
        headerText = stringResource(Res.string.welcome_to_chat),
        errorText = null,
        logo = { NeroBrandLogo() }
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            NeroTextField(
                state = emailState,
                title = stringResource(Res.string.email),
                placeholder = stringResource(Res.string.email_placeholder),
                supportingText = uiState.emailError?.asString(),
                isError = uiState.emailError != null,
            )
            NeroTextField(
                state = displayNameState,
                title = stringResource(Res.string.display_name_placeholder),
                placeholder = stringResource(Res.string.display_name_placeholder),
                supportingText = uiState.displayNameError?.asString(),
                isError = uiState.displayNameError != null,
            )
            NeroPasswordTextField(
                state = passwordState,
                title = stringResource(Res.string.password),
                placeholder = stringResource(Res.string.password),
                supportingText = uiState.passwordError?.asString()
                    ?: stringResource(Res.string.password_hint),
                isError = uiState.passwordError != null,
            )
            Column {
                NeroButton(
                    text = stringResource(Res.string.register),
                    onClick = { onIntent(RegisterIntent.SubmitRegistration) },
                    isLoading = uiState.isRegistering,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                NeroButton(
                    text = stringResource(Res.string.login),
                    onClick = { onIntent(RegisterIntent.NavigateToLogin) },
                    style = NeroButtonStyle.Secondary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@PreviewLightDark
@PreviewScreenSizes
@Composable
private fun RegisterPreview() {
    NeroTheme {
        RegisterScreen(
            uiState = RegisterUiState(),
            emailState = TextFieldState(),
            displayNameState = TextFieldState(),
            passwordState = TextFieldState(),
            onIntent = {},
        )
    }
}