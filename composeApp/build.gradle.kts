import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.hot.reload)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.metro.compiler)
}

kotlin {
    android {
        compileSdk = 36
        minSdk = 28
        namespace = "io.github.neronguyenvn.nerochat.composeapp"

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
        commonMain.dependencies {
            implementation(projects.core.designsystem)
            implementation(projects.core.data)
            implementation(projects.feature.auth.presentation)
            implementation(libs.jetbrains.compose.components.resources)
            implementation(libs.jetbrains.compose.material3)
            implementation(libs.jetbrains.compose.ui.tooling.preview)
            implementation(libs.metrox.viewmodel.compose)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.jetbrains.compose.ui.tooling)
}

compose.desktop {
    application {
        mainClass = "io.github.neronguyenvn.nerochat.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "io.github.neronguyenvn.nerochatapp"
            packageVersion = "1.0.0"
        }
    }
}
