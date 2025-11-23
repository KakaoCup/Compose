plugins {
    id("convention.library")
    id("convention.publishing")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "io.github.kakaocup.compose.ui"
}

dependencies {
    implementation(project(":compose-semantics"))
    implementation(libs.androidx.compose.material)
}
