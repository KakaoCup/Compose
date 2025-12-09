plugins {
    id("convention.library")
    id("convention.publishing")
}

android {
    namespace = "io.github.kakaocup.compose"
}

dependencies {
    implementation(libs.androidx.test.espresso.espressoCore)
    implementation(libs.androidx.test.ext.junit)

    implementation(libs.androidx.compose.ui.uiTestJunit4)
}
