plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig.apply {
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerVersion = Versions.kotlin
        kotlinCompilerExtensionVersion = Versions.Compose.compose
    }

    packagingOptions {
        resources.excludes.add("META-INF/*")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "11"
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
    implementation(Libraries.appcompat)
    implementation(Libraries.material)
    implementation(Libraries.multidex)

    implementation(Libraries.Compose.activityCompose)
    implementation(Libraries.Compose.uiTooling)
    implementation(Libraries.Compose.material)
    debugImplementation(Libraries.Compose.testManifest)

    implementation(Libraries.junit)
    implementation(Libraries.espresso_core)

    androidTestImplementation(project(":compose"))
    implementation(Libraries.Compose.junit)

    implementation(Libraries.junit_ext)
}
