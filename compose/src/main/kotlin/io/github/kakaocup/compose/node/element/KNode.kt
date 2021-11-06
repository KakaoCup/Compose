package io.github.kakaocup.compose.node.element

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.node.core.BaseNode

open class KNode : BaseNode<KNode> {

    internal constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        nodeMatcher: NodeMatcher,
        parentNode: BaseNode<KNode>? = null,
    ) : super(semanticsProvider, nodeMatcher, parentNode)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        viewBuilderAction: ViewBuilder.() -> Unit,
    ) : super(semanticsProvider, viewBuilderAction)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        nodeMatcher: NodeMatcher,
    ) : super(semanticsProvider, nodeMatcher)
}