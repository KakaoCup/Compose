plugins {
    id("convention.library")
    id("convention.publishing")
}

android {
    namespace = "io.github.kakaocup.appium"
}

dependencies {
    implementation(libs.androidx.test.ext.junit)

    implementation(libs.java.client)
    implementation(libs.selenium.java)

    implementation(kotlin("test"))
}
