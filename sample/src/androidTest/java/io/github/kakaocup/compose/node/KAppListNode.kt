package io.github.kakaocup.compose.node

import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.node.element.list.KListNode
import io.github.kakaocup.compose.sample.semantics.ListItemIndexSemanticKey
import io.github.kakaocup.compose.sample.semantics.ListLengthSemanticKey

fun BaseNode<*>.KAppListNode(
    testTag: String,
    isScrollable: Boolean = true,
) = KListNode(
    useUnmergedTree = true,
    itemIndexSemanticsPropertyKey = ListItemIndexSemanticKey,
    lengthSemanticsPropertyKey = ListLengthSemanticKey,
    isScrollable = isScrollable,
) {
    hasTestTag(testTag)
}
