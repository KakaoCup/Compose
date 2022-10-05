import java.io.File

object PackageInfo {
    val version = File("buildsystem/version").readText().trim()
    const val groupId = "io.github.kakaocup"
}
