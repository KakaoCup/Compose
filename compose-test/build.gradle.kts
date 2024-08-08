plugins {
    id("convention.library")
    id("convention.publishing")
}

android {
    namespace = "io.github.kakaocup.compose.test"
}

dependencies {
    implementation(project(":compose"))
    implementation(project(":compose-semantics"))
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.compose.ui.uiTooling)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.uiTestJunit4)
}
