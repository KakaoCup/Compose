package io.github.kakaocup.compose.screen.list

import io.github.kakaocup.compose.node.KAppListNode
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.list.KListItemNode

class NotScrollableListComposeScreen : ComposeScreen<NotScrollableListComposeScreen>() {

    val notScrollableListNode = KAppListNode(
        testTag = "tabbar_container",
        isScrollable = false,
    )

    class TabBarListItemNode : io.github.kakaocup.compose.node.element.list.KListItemNode<TabBarListItemNode>() {

        val text: KNode by lazy {
            child {
                hasTestTag("tabbar_item_text")
            }
        }

        val icon: KNode by lazy {
            child {
                hasTestTag("tabbar_item_icon")
            }
        }

    }

}