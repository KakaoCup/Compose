plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 34
    namespace = "io.github.kakaocup.compose"
    defaultConfig.apply {
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    signingConfigs {
        create("kakao") {
            storeFile = File("${project.rootDir}/buildsystem/debug.keystore")
            storePassword = "android"
            keyAlias = "kakaodebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        debug { signingConfig = signingConfigs.getByName("kakao") }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerVersion.get()
    }

    packaging.resources.excludes.add("META-INF/*")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    testOptions {
        animationsDisabled = true
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
            res.srcDir("src/main/res")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.com.google.android.material)
    implementation(libs.androidx.multidex.multidex)

    implementation(libs.androidx.activity.activityCompose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.uiTooling)
    implementation(libs.androidx.compose.material)
    debugImplementation(libs.androidx.compose.ui.uiTestManifest)

    implementation(libs.junit.junit)
    implementation(libs.androidx.test.espresso.espressoCore)

    androidTestImplementation(project(":compose"))
    implementation(libs.androidx.compose.ui.uiTestJunit4)

    implementation(libs.androidx.test.ext.junit)
}
