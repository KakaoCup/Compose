package io.github.kakaocup.compose.node

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.node.element.KTextNode
import io.github.kakaocup.compose.sample.semantics.TextColorSemanticKey

class KAppTextNode(
    semanticsProvider: SemanticsNodeInteractionsProvider?,
    nodeMatcher: NodeMatcher,
    parentNode: BaseNode<*>? = null,
) : KTextNode(
    semanticsProvider = semanticsProvider,
    nodeMatcher = nodeMatcher,
    parentNode = parentNode,
    useUnmergedTree = true
) {
    override val textColorSemanticsPropertyKey: SemanticsPropertyKey<Color> = TextColorSemanticKey
}
