package io.github.neronguyenvn.core.data.network

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

@BindingContainer
@ContributesTo(AppScope::class)
object JvmNetworkBindings {

    @Provides
    fun provideHttpClientEngine(): HttpClientEngine {
        return OkHttp.create()
    }
}