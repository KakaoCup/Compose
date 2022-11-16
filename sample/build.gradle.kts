plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 33
    defaultConfig.apply {
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerVersion.get()
    }

    packagingOptions {
        resources.excludes.add("META-INF/*")
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
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.multidex)

    implementation(libs.composeActivityCompose)
    implementation(libs.composeUiTooling)
    implementation(libs.composeMaterial)
    debugImplementation(libs.composeTestManifest)

    implementation(libs.junit)
    implementation(libs.espressoCore)

    androidTestImplementation(project(":compose"))
    implementation(libs.composeJunit)

    implementation(libs.junitExt)
}
