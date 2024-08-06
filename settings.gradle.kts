include(":compose")
include(":ui")
include(":ui-test")
include(":sample")

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("org.gradle.toolchains:foojay-resolver:0.7.0")
    }
}

apply {
    plugin("org.gradle.toolchains.foojay-resolver-convention")
}