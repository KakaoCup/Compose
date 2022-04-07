package io.github.kakaocup.compose.node.element.lazy_list

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import kotlin.reflect.KClass

/**
 * Class that maps types to providing functions
 *
 * To be able to support different item types in KLazyListNode, this class
 * adds support for mapping item type classes to functions that provide them.
 *
 * @see itemType
 */
class KLazyListItemBuilder {
    val itemTypes = mutableMapOf<KClass<out KLazyListItemNode>, KListItemType<KLazyListItemNode>>()

    /**
     * Adds entry that helps KLazyListNode to automatically build child nodes
     *
     * @param provideItem Function that takes params and returns instance of item node
     */
    inline fun <reified T : KLazyListItemNode> itemType(noinline provideItem: (SemanticsNode, SemanticsNodeInteractionsProvider) -> T) {
        itemTypes[T::class] = KListItemType(provideItem)
    }
}

class KListItemType<out T : KLazyListItemNode>(val provideItem: (SemanticsNode, SemanticsNodeInteractionsProvider) -> T)