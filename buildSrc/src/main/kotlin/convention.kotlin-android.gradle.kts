import io.github.kakaocup.withVersionCatalog
import org.gradle.kotlin.dsl.kotlin
import kotlin.text.toInt

plugins {
    kotlin("android")
}

withVersionCatalog { libs ->
    kotlin {
        jvmToolchain(libs.versions.jvmVersion.get().toInt())
    }
}
