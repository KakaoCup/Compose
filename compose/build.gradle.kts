plugins {
    id("com.android.library")
    kotlin ("android")
    //id("org.jetbrains.dokka")
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks {
    withType<JavaCompile> {
        options.isFork = true

        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.androidx.test.espresso.espressoCore)
    //implementation(libs.androidx.test.ext.junit)

    //implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.compose.ui:ui-test-junit4-android:1.5.4")

    //dokkaHtmlPlugin(libs.org.jetbrains.dokka.kotlinAsJavaPlugin)
}

/*
tasks.dokkaGfm {
    moduleName.set("compose")
    outputDirectory.set(File("$rootDir/docs"))
}
tasks.dokkaHtml.configure {
    moduleName.set("compose")
    outputDirectory.set(File("$rootDir/html"))
}

 */

afterEvaluate {
    //Deployment.initialize(project)
}
