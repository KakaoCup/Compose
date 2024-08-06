plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0")
    implementation("com.android.tools.build:gradle:8.5.0")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.20")
    implementation("org.kohsuke:github-api:1.129")
}
