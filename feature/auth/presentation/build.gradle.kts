plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.metro.compiler)
}

kotlin {
    android {
        compileSdk = 36
        minSdk = 28
        namespace = "io.github.neronguyenvn.auth.presentation"

        androidResources {
            enable = true
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.designsystem)
                implementation(projects.core.domain)
                implementation(projects.core.presentation)
                implementation(projects.feature.auth.domain)
                implementation(libs.jetbrains.compose.components.resources)
                implementation(libs.jetbrains.compose.material3)
                implementation(libs.jetbrains.compose.ui.tooling.preview)
                implementation(libs.jetbrains.lifecycle.runtime.compose)
                implementation(libs.jetbrains.lifecycle.viewmodel.compose)
                implementation(libs.arrow.core)
                implementation(libs.metrox.viewmodel.compose)
            }
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.jetbrains.compose.ui.tooling)
}