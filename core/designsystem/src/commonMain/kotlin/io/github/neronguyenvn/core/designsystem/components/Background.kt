package io.github.neronguyenvn.core.designsystem.components

import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview

/**
 * Multipreview annotation that represents light and dark themes. Add this annotation to a
 * composable to render the both themes.
 */
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES, name = "Dark theme")
annotation class ThemePreviews