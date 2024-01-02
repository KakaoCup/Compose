package io.github.kakaocup.compose.node

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.node.element.KIconNode
import io.github.kakaocup.compose.sample.semantics.ImageContentSemanticKey
import io.github.kakaocup.compose.sample.semantics.TintColorSemanticKey

class KAppIconNode(
    semanticsProvider: SemanticsNodeInteractionsProvider,
    nodeMatcher: NodeMatcher,
    parentNode: BaseNode<*>? = null,
) : KIconNode(
    semanticsProvider = semanticsProvider,
    nodeMatcher = nodeMatcher,
    parentNode = parentNode,
    useUnmergedTree = true
) {
    override val imageContentSemanticsPropertyKey: SemanticsPropertyKey<Any> = ImageContentSemanticKey
    override val tintColorSemanticsPropertyKey: SemanticsPropertyKey<Color> = TintColorSemanticKey
}
