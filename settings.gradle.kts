include(":foundation")
include(":ui")
include(":ui-test")
include(":lazylist")
include(":semantics")
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