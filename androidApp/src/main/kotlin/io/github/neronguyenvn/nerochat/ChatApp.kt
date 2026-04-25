package io.github.neronguyenvn.nerochat

import android.app.Application
import dev.zacsweers.metro.createGraph
import dev.zacsweers.metrox.android.MetroAppComponentProviders
import dev.zacsweers.metrox.android.MetroApplication

class ChatApp : Application(), MetroApplication {

    override val appComponentProviders: MetroAppComponentProviders by lazy {
        createGraph<AppGraph>()
    }
}