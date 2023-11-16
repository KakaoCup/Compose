plugins {
    id("com.android.library")
    kotlin ("android")
    id("org.jetbrains.dokka")
}

android {
    namespace = "io.github.kakaocup.compose"
    compileSdk = 34
    defaultConfig.apply {
        minSdk = 21
        targetSdk = 34
    }



    packaging.resources.excludes.add("META-INF/*")

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
            res.srcDir("src/main/res")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
    }
}

dependencies {
    implementation(libs.androidx.test.espresso.espressoCore)
    implementation(libs.androidx.test.ext.junit)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.uiTestJunit4)

    dokkaHtmlPlugin(libs.org.jetbrains.dokka.kotlinAsJavaPlugin)
}

tasks.dokkaGfm {
    moduleName.set("compose")
    outputDirectory.set(File("$rootDir/docs"))
}
tasks.dokkaHtml.configure {
    moduleName.set("compose")
    outputDirectory.set(File("$rootDir/html"))
}

kotlin.jvmToolchain(17)
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>().configureEach {
    jvmTargetValidationMode.set(org.jetbrains.kotlin.gradle.dsl.jvm.JvmTargetValidationMode.WARNING)
}

afterEvaluate {
    Deployment.initialize(project)
}
