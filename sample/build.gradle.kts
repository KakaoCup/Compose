plugins {
    id("convention.application")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "io.github.kakaocup.appium.sample"

    defaultConfig.apply {
        versionCode = 1
        versionName = "1.0.0"
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerVersion.get()
    }

    packaging {
        resources {
            pickFirsts += "META-INF/androidx.compose.*.version"
            excludes += "META-INF/*"
        }
    }
}

dependencies {
    testImplementation(project(":appium"))

    testImplementation(libs.java.client)
    testImplementation(libs.selenium.java)

    implementation(libs.androidx.appcompat)
    implementation(libs.com.google.android.material)
    implementation(libs.androidx.multidex.multidex)

    implementation(libs.androidx.activity.activityCompose)

    implementation(libs.androidx.compose.ui.uiTooling)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.iconsCore)
    debugImplementation(libs.androidx.compose.ui.uiTestManifest)

    implementation(libs.junit.junit)

    implementation(libs.androidx.compose.ui.uiTestJunit4)
    implementation(libs.androidx.test.ext.junit)
}
