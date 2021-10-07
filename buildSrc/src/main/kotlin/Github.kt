import org.kohsuke.github.GitHubBuilder

class Github(private val token: String) {
    private val github by lazy { GitHubBuilder().withOAuthToken(token).build() }
    private val composeRepo by lazy { github.getRepository("kakaocup/compose") }

    val collaborators by lazy { composeRepo.collaborators }
    val contributors by lazy { composeRepo.statistics.contributorStats.map { it.author }.toSet() }
}
