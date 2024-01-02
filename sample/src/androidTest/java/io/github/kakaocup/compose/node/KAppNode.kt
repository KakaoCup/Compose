package io.github.kakaocup.compose.node

import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.assertion.BackgroundAssertions
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.sample.semantics.BackgroundColorSemanticKey
import io.github.kakaocup.compose.sample.semantics.BackgroundShapeSemanticKey

class KAppNode(
    semanticsProvider: SemanticsNodeInteractionsProvider,
    nodeMatcher: NodeMatcher,
    parentNode: BaseNode<*>? = null,
) : BaseNode<KAppNode>(
    semanticsProvider = semanticsProvider,
    nodeMatcher = nodeMatcher,
    parentNode = parentNode,
), BackgroundAssertions {
    override val backgroundColorSemanticsPropertyKey: SemanticsPropertyKey<Any> = BackgroundColorSemanticKey
    override val backgroundShapeSemanticsPropertyKey: SemanticsPropertyKey<Shape> = BackgroundShapeSemanticKey
}
