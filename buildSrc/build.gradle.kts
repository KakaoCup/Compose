plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")
    implementation("com.android.tools.build:gradle:8.2.1")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.10")
    implementation("org.kohsuke:github-api:1.318")
}
