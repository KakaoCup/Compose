plugins {
    id("com.android.library")
    kotlin ("android")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "io.github.kakaocup.compose"
    compileSdk = 34
    defaultConfig.apply {
        minSdk = 21
        targetSdk = 34
    }

    packaging.resources.excludes.add("META-INF/*")

    sourceSets {
        getByName("main") {
            kotlin.srcDirs("src/main/kotlin")
            res.srcDir("src/main/res")
        }
        getByName("test") {
            kotlin.srcDirs("src/test/kotlin")
        }
    }

    publishing {
        singleVariant("release") {
            withJavadocJar()
            withSourcesJar()
        }
    }
}

kotlin {
    jvmToolchain(8)
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.uiTooling)
}
