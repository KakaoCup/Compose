package io.github.kakaocup.compose.screen.navigation

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.node.element.KNode

class SharedWidgetNode(
    semanticsProvider: SemanticsNodeInteractionsProvider,
    nodeMatcher: NodeMatcher,
    parentNode: BaseNode<*>? = null
) : BaseNode<SharedWidgetNode>(semanticsProvider, nodeMatcher, parentNode) {
    val title: KNode = child {
        hasTestTag("SharedWidgetTitle")
    }
}