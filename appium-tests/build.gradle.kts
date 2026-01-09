plugins {
    id("convention.library")
}

dependencies {
    testImplementation(project(":appium"))
    testImplementation(kotlin("test"))
    testImplementation(libs.junit.junit)
    testImplementation(libs.java.client)
}
