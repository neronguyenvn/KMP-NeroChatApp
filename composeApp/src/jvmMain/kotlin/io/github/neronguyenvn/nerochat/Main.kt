package io.github.neronguyenvn.nerochat

import androidx.compose.ui.window.singleWindowApplication
import dev.zacsweers.metro.createGraph

fun main() {
    val appGraph = createGraph<AppGraph>()
    singleWindowApplication(title = "Chat App") {
        ComposeApp(viewModelFactory = appGraph.metroViewModelFactory)
    }
}