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
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material)
}
