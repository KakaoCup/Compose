plugins {
    id("com.android.library")
    kotlin ("android")
    id("org.jetbrains.dokka")
}

android {
    compileSdkVersion(33)
    defaultConfig.apply {
        minSdk = 21
        targetSdk = 33
    }

    packagingOptions {
        resources.excludes.add("META-INF/*")
    }

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
    implementation(libs.espressoCore)
    implementation(libs.composeJunit)
    implementation(libs.junitExt)

    dokkaHtmlPlugin(libs.dokka)
}

tasks.dokkaGfm {
    moduleName.set("compose")
    outputDirectory.set(File("$rootDir/docs"))
}
tasks.dokkaHtml.configure {
    moduleName.set("compose")
    outputDirectory.set(File("$rootDir/html"))
}

afterEvaluate {
    Deployment.initialize(project)
}
