package io.github.kakaocup.compose.node.element

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.assertion.ImageContentAssertions
import io.github.kakaocup.compose.node.assertion.TintColorAssertions
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.core.BaseNode

abstract class KIconNode(
    semanticsProvider: SemanticsNodeInteractionsProvider,
    nodeMatcher: NodeMatcher,
    parentNode: BaseNode<*>?,
    useUnmergedTree: Boolean = false
) : BaseNode<KIconNode>(
    semanticsProvider = semanticsProvider,
    nodeMatcher = nodeMatcher.copy(useUnmergedTree = useUnmergedTree),
    parentNode = parentNode
), ImageContentAssertions, TintColorAssertions
