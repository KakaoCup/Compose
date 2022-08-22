import java.io.File

object Versions {
    val composeVersion = File("buildsystem/version").readText().trim()

    val kotlin = "1.7.0"
    val dokka = "1.4.32"
    val appcompat = "1.3.0"
    val espresso = "3.4.0"
    val junit = "4.13.2"
    val junit_ext = "1.1.3"
    val multidex = "2.0.1"
    val material = "1.4.0"

    object Compose {
        const val activity_compose = "1.5.1"
        const val compose = "1.2.0"
    }
}

object Libraries {
    object Compose {
        val activityCompose =
            "androidx.activity:activity-compose:${Versions.Compose.activity_compose}"
        val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.Compose.compose}"
        val junit = "androidx.compose.ui:ui-test-junit4:${Versions.Compose.compose}"
        val material = "androidx.compose.material:material:${Versions.Compose.compose}"
        val testManifest = "androidx.compose.ui:ui-test-manifest:${Versions.Compose.compose}"
    }
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val dokka = "org.jetbrains.dokka:kotlin-as-java-plugin:${Versions.dokka}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val junit = "junit:junit:${Versions.junit}"
    val junit_ext = "androidx.test.ext:junit:${Versions.junit_ext}"
    val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    val material = "com.google.android.material:material:${Versions.material}"
}

object Description {
    val mavenGroup = "io.github.kakaocup"
}
