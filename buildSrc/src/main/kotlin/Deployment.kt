@file:Suppress("DEPRECATION")

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPomContributorSpec
import org.gradle.api.publish.maven.MavenPomDeveloperSpec
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.the
import org.gradle.plugins.signing.SigningExtension
import java.net.URI

object Deployment {
    val ghToken = System.getenv("GH_TOKEN")

    fun initialize(project: Project) {
        val releaseMode: String? by project
        initializePublishing(project, releaseMode)
        initializeSigning(project)
    }

    private fun initializePublishing(project: Project, releaseMode: String?) {
        val versionSuffix = when (releaseMode) {
            "RELEASE" -> ""
            "SNAPSHOT" -> "-SNAPSHOT"
            else -> throw Exception("Unknown release mode")
        }

        project.version = PackageInfo.version + versionSuffix

        project.plugins.apply("maven-publish")

        project.configure<PublishingExtension> {
            publications {
                create("default", MavenPublication::class.java) {
                    groupId = PackageInfo.groupId
                    customizePom(pom)
                    from(project.components["release"])
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
                        username = System.getenv("SONATYPE_USERNAME")
                        password = System.getenv("SONATYPE_PASSWORD")
                    }
                    url = URI.create(
                        when (releaseMode) {
                            "RELEASE" -> System.getenv("SONATYPE_RELEASES_URL")
                                ?: "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"

                            else -> System.getenv("SONATYPE_SNAPSHOTS_URL")
                                ?: "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                        }
                    )
                }

                maven {
                    name = "Github"
                    setUrl("https://maven.pkg.github.com/vacxe/konveyor")
                    credentials {
                        username = System.getenv("GITHUB_ACTOR")
                        password = System.getenv("GITHUB_TOKEN")
                    }
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
            project.extra.set(
                "signing.secretKeyRingFile",
                "${project.rootProject.rootDir}/buildsystem/secring.gpg"
            )
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
