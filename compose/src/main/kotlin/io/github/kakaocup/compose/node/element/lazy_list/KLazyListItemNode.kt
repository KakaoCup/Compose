package io.github.kakaocup.compose.node.element.lazy_list

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
abstract class KLazyListItemNode(
    semanticNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider,
) : BaseNode<KLazyListItemNode>(
    semanticsProvider,
    NodeMatcher(
        matcher = SemanticsMatcher(
            description = "Lazy list item matcher",
            matcher = { it.id == semanticNode.id },
        )
    ),
)