plugins {
    id("convention.library")
    id("convention.publishing")
}

android {
    namespace = "io.github.kakaocup.compose.semantics"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.uiTooling)
}
