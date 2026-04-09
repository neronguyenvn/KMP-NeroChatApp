package io.github.neronguyenvn.core.designsystem.util

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowSizeClass.Companion.HEIGHT_DP_EXPANDED_LOWER_BOUND
import androidx.window.core.layout.WindowSizeClass.Companion.HEIGHT_DP_MEDIUM_LOWER_BOUND
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_EXPANDED_LOWER_BOUND
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND

@Composable
fun currentWindowConfig(): WindowConfig {
    return currentWindowAdaptiveInfo().windowSizeClass.asWindowConfig()
}

enum class WindowConfig {
    MobilePortrait, MobileLandscape,
    TabletPortrait, TabletLandscape,
    Desktop
}

private fun WindowSizeClass.asWindowConfig(): WindowConfig {
    return when {
        !isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
            WindowConfig.MobilePortrait
        }

        !isHeightAtLeastBreakpoint(HEIGHT_DP_MEDIUM_LOWER_BOUND) -> {
            WindowConfig.MobileLandscape
        }

        isWidthAtLeastBreakpoint(WIDTH_DP_EXPANDED_LOWER_BOUND) &&
                isHeightAtLeastBreakpoint(HEIGHT_DP_EXPANDED_LOWER_BOUND) -> {
            WindowConfig.Desktop
        }

        isHeightAtLeastBreakpoint(HEIGHT_DP_EXPANDED_LOWER_BOUND) -> {
            WindowConfig.TabletPortrait
        }

        else -> WindowConfig.TabletLandscape
    }
}