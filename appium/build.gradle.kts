plugins {
    id("convention.library")
    id("convention.publishing")
}

dependencies {
    implementation(libs.java.client)
    implementation(libs.selenium.java)
    implementation(libs.junit.junit)

    implementation(kotlin("test"))
}
