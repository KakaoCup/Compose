@file:Suppress("DEPRECATION")

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPomContributorSpec
import org.gradle.api.publish.maven.MavenPomDeveloperSpec
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.creating
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.the
import org.gradle.plugins.signing.SigningExtension
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URI

object Deployment {
    val ghToken = System.getenv("GH_TOKEN")
    val sonatypeUser = System.getenv("SONATYPE_USERNAME")
    val sonatypePassword = System.getenv("SONATYPE_PASSWORD")
    var releaseMode: String? = null
    var versionSuffix: String? = null
    var deployUrl: String? = null

    val snapshotDeployUrl = System.getenv("SONATYPE_SNAPSHOTS_URL")
        ?: "https://s01.oss.sonatype.org/content/repositories/snapshots/"
    val releaseDeployUrl = System.getenv("SONATYPE_RELEASES_URL")
        ?: "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"

    fun initialize(project: Project) {
        val releaseMode: String? by project
        val versionSuffix = when (releaseMode) {
            "RELEASE" -> ""
            else -> "-SNAPSHOT"
        }

        Deployment.releaseMode = releaseMode
        Deployment.versionSuffix = versionSuffix
        deployUrl = when (releaseMode) {
            "RELEASE" -> releaseDeployUrl
            else -> snapshotDeployUrl
        }

        initializePublishing(project)
        initializeSigning(project)
    }

    private fun initializePublishing(project: Project) {
        project.version = PackageInfo.version + versionSuffix

        project.plugins.apply("maven-publish")

        val (component, additionalArtifacts) = when {
            project.extensions.findByType(LibraryExtension::class) != null -> {
                val android = project.extensions.findByType(LibraryExtension::class)!!
                val main = android.sourceSets.getByName("main")
                val sourcesJar by project.tasks.creating(Jar::class) {
                    archiveClassifier.set("sources")
                    from(main.java.srcDirs)
                }
                val javadocJar by project.tasks.creating(Jar::class) {
                    archiveClassifier.set("javadoc")
                    val dokka = project.tasks.findByName("dokkaJavadoc") as DokkaTask
                    from(dokka.outputDirectory)
                    dependsOn(dokka)
                }

                Pair(project.components["release"], listOf(sourcesJar, javadocJar))
            }
            project.the(JavaPluginConvention::class) != null -> {
                val javaPlugin = project.the(JavaPluginConvention::class)

                val sourcesJar by project.tasks.creating(Jar::class) {
                    archiveClassifier.set("sources")
                    from(javaPlugin.sourceSets["main"].allSource)
                }
                val javadocJar by project.tasks.creating(Jar::class) {
                    archiveClassifier.set("javadoc")
                    from(javaPlugin.docsDir)
                    dependsOn("javadoc")
                }

                Pair(project.components["java"], listOf(sourcesJar, javadocJar))
            }
            else -> {
                throw RuntimeException("Unknown plugin")
            }
        }

        project.configure<PublishingExtension> {
            publications {
                create("default", MavenPublication::class.java) {
                    groupId = PackageInfo.groupId
                    customizePom(pom)
                    additionalArtifacts.forEach {
                        artifact(it)
                    }
                    from(component)
                }
            }
            repositories {
                maven {
                    name = "Local"
                    setUrl("${project.rootDir}/build/repository")
                }
                maven {
                    name = "OSSHR"
                    credentials {
                        username = sonatypeUser
                        password = sonatypePassword
                    }
                    url = URI.create(deployUrl)
                }
            }
        }
    }

    private fun initializeSigning(project: Project) {
        val passphrase = System.getenv("GPG_PASSPHRASE")
        passphrase?.let {
            project.plugins.apply("signing")

            val publishing = project.the(PublishingExtension::class)
            project.configure<SigningExtension> {
                sign(publishing.publications.getByName("default"))
            }

            project.extra.set("signing.keyId", "0110979F")
            project.extra.set("signing.password", passphrase)
            project.extra.set("signing.secretKeyRingFile", "${project.rootProject.rootDir}/buildsystem/secring.gpg")
        }
    }

    fun customizePom(pom: MavenPom?) {
        pom?.apply {
            name.set("compose")
            url.set("https://github.com/KakaoCup/Compose")
            description.set("Nice and simple DSL for Espresso in Kotlin")

            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }

            developers(findCollaborators())
            //contributors(findContributors()) Failing

            scm {
                url.set("https://github.com/KakaoCup/Compose.git")
                connection.set("scm:git:ssh://github.com/KakaoCup/Compose")
                developerConnection.set("scm:git:ssh://github.com/KakaoCup/Compose")
            }
        }
    }

    private fun findCollaborators() = Action<MavenPomDeveloperSpec> {
        if (!ghToken.isNullOrEmpty()) {
            Github(ghToken).collaborators.forEach {
                developer {
                    id.set(it.login)
                    url.set("https://github.com/${it.login}")
                    name.set(it.name)
                }
            }
        }
    }

    private fun findContributors() = Action<MavenPomContributorSpec> {
        if (!ghToken.isNullOrEmpty()) {
            Github(ghToken).contributors.sortedBy { it.login }.forEach {
                contributor {
                    name.set(it.login)
                    url.set("https://github.com/${it.login}")
                }
            }
        }
    }
}
