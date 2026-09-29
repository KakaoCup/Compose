plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
    alias(libs.plugins.jetbrains.compose)
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    jvmToolchain(libs.versions.jvmVersion.get().toInt())

    android {
        namespace = "io.github.kakaocup.compose"
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
    }

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.jetbrains.compose.runtime)
            implementation(libs.jetbrains.compose.foundation)
            implementation(libs.jetbrains.compose.ui)
            implementation(libs.jetbrains.compose.ui.test)
        }
        androidMain.dependencies {
            implementation(libs.androidx.compose.ui.uiTestJunit4)
            implementation(libs.androidx.test.ext.junit)
        }
    }
}
