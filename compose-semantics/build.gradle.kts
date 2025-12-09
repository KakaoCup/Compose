plugins {
    id("convention.library")
    id("convention.publishing")
}

android {
    namespace = "io.github.kakaocup.compose.semantics"
}

dependencies {
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.uiTooling)
}
