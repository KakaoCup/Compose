package io.github.kakaocup.compose.screen.list

import io.github.kakaocup.compose.node.KAppListNode
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.list.KListItemNode

class ScrollableLazyListComposeScreen : ComposeScreen<ScrollableLazyListComposeScreen>() {

    val lazyList = KAppListNode("LazyList")

    val pullToRefresh = KNode() { hasTestTag("PullToRefresh") }

    class ListHeaderNode : io.github.kakaocup.compose.node.element.list.KListItemNode<ListHeaderNode>() {

        val title: KNode by lazy {
            child {
                hasTestTag("LazyListHeaderTitle")
            }
        }
    }

}
