package io.github.kakaocup.compose.node.element

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.assertion.TextColorAssertions
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.core.BaseNode

abstract class KTextNode(
    semanticsProvider: SemanticsNodeInteractionsProvider,
    nodeMatcher: NodeMatcher,
    parentNode: BaseNode<*>?,
    useUnmergedTree: Boolean = false
) : BaseNode<KTextNode>(
    semanticsProvider = semanticsProvider,
    nodeMatcher = nodeMatcher.copy(useUnmergedTree = useUnmergedTree),
    parentNode = parentNode
), TextColorAssertions
