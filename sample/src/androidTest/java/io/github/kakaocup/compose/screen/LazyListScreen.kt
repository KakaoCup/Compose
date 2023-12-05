package io.github.kakaocup.compose.screen

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListNode
import io.github.kakaocup.compose.sample.LazyListItemPositionSemantics
import io.github.kakaocup.compose.sample.LazyListLengthSemantics

class LazyListScreen(semanticsProvider: SemanticsNodeInteractionsProvider) : ComposeScreen<LazyListScreen>(
    semanticsProvider = semanticsProvider,
    viewBuilderAction = { hasTestTag("LazyListScreen") }
) {
    val list = KLazyListNode(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag("LazyList") },
        itemTypeBuilder = {
            itemType(::LazyListItemNode)
            itemType(::LazyListHeaderNode)
        },
        positionMatcher = { position -> SemanticsMatcher.expectValue(LazyListItemPositionSemantics, position) },
        lengthSemanticsPropertyKey = LazyListLengthSemantics
    )

    val pullToRefresh = KNode(semanticsProvider) { hasTestTag("PullToRefresh") }
}

class LazyListItemNode(
    semanticsNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider,
) : KLazyListItemNode<LazyListItemNode>(semanticsNode, semanticsProvider)

class LazyListHeaderNode(
    semanticsNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider,
) : KLazyListItemNode<LazyListHeaderNode>(semanticsNode, semanticsProvider) {
    val title: KNode = child {
        hasTestTag("LazyListHeaderTitle")
    }
}
