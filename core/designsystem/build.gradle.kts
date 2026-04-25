plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.kotlin.multiplatform.library)
}

kotlin {
    android {
        compileSdk = 36
        minSdk = 28
        namespace = "io.github.neronguyenvn.core.designsystem"

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
                implementation(libs.jetbrains.compose.components.resources)
                implementation(libs.jetbrains.compose.material3)
                implementation(libs.jetbrains.compose.material3.adaptive.layout)
                implementation(libs.jetbrains.compose.material.icons.core)
                implementation(libs.jetbrains.compose.ui.tooling.preview)
            }
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.jetbrains.compose.ui.tooling)
}