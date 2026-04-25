plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.metro.compiler)
}

android {
    namespace = "io.github.neronguyenvn.nerochat.androidapp"

    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 28
        applicationId = "io.github.neronguyenvn.nerochat"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.composeApp)
    implementation(libs.jetbrains.compose.ui.tooling.preview)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.metro.android)
    implementation(libs.metrox.viewmodel.compose)
}