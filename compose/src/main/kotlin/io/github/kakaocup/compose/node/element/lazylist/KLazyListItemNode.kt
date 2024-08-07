package io.github.kakaocup.compose.node.element.lazylist

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.core.BaseNode

/**
 * Base class for KLazyListNode items
 *
 * Matches LazyList children by given semantics node id
 *
 * @param semanticNode A list of key/value pairs associated with a layout node or its subtree
 * @param semanticsProvider Provides main entry point into testing
 */
abstract class KLazyListItemNode<out T : KLazyListItemNode<T>>(
    semanticNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider? = null,
) : BaseNode<T>(
    semanticsProvider,
    NodeMatcher(
        matcher = SemanticsMatcher(
            description = "Semantics node id = ${semanticNode.id}",
            matcher = { it.id == semanticNode.id },
        )
    ),
)
