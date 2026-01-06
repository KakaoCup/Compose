import org.kohsuke.github.GitHubBuilder

class Github(private val token: String) {
    private val github by lazy { GitHubBuilder().withOAuthToken(token).build() }
    private val appiumRepo by lazy { github.getRepository("kakaocup/appium") }

    val collaborators by lazy { appiumRepo.collaborators }
    val contributors by lazy { appiumRepo.statistics.contributorStats.map { it.author }.toSet() }
}
