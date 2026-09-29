package io.github.kakaocup.compose.node.builder

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider

class NodeProvider(
    val nodeMatcher: NodeMatcher,
    private val semanticsProvider: SemanticsNodeInteractionsProvider,
) {

    fun provideSemanticsNodeInteraction(): SemanticsNodeInteraction {
        return semanticsProvider.onAllNodes(
            nodeMatcher.matcher,
            nodeMatcher.useUnmergedTree
        )[nodeMatcher.position]
    }
}