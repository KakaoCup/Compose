package io.github.kakaocup.compose.node.element.lazylist

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.onChildren
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.node.core.BaseNode

/**
 * Node class with special api to test Lazy List (LazyColumn or LazyRow)
 */
class KLazyListNode(
    semanticsProvider: SemanticsNodeInteractionsProvider,
    nodeMatcher: NodeMatcher,
    itemTypeBuilder: KLazyListItemBuilder.() -> Unit,
    val positionMatcher: (position: Int) -> SemanticsMatcher
) : BaseNode<KLazyListNode>(semanticsProvider, nodeMatcher) {
    val semanticsMatcher = nodeMatcher.matcher
    val itemTypes = KLazyListItemBuilder().apply(itemTypeBuilder).itemTypes

    /**
     * Constructs node class with node interaction from given ViewBuilder
     *
     * @param semanticsProvider Provides main entry point into testing
     * @param viewBuilderAction ViewBuilder which will result in view's interaction
     * @param itemTypeBuilder Lambda with receiver where you pass your item providers
     * @param positionMatcher Lambda which finds node by given position
     *
     * @see ViewBuilder
     */
    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        viewBuilderAction: ViewBuilder.() -> Unit,
        itemTypeBuilder: KLazyListItemBuilder.() -> Unit,
        positionMatcher: (position: Int) -> SemanticsMatcher
    ) : this(
        semanticsProvider = semanticsProvider,
        nodeMatcher = ViewBuilder().apply(viewBuilderAction).build(),
        itemTypeBuilder = itemTypeBuilder,
        positionMatcher = positionMatcher
    )

    /**
     * Performs given actions/assertion on child at given position
     *
     * @param T Type of item at given position. Must be registered via constructor.
     * @param position Position of item in lazy list
     * @param function Tail lambda which receiver will be matched item with given type T
     */
    @ExperimentalTestApi
    inline fun <reified T : KLazyListItemNode> childAt(
        position: Int,
        function: T.() -> Unit
    ) {
        val provideItem = itemTypes.getOrElse(T::class) {
            throw LazyListItemProvisionException(T::class)
        }.provideItem

        performScrollToIndex(position)

        val semanticNode = semanticsProvider
            .onNode(semanticsMatcher)
            .onChildren()
            .filterToOne(positionMatcher(position))
            .fetchSemanticsNode()

        function(provideItem(semanticNode, semanticsProvider) as T)
    }

    /**
     * Performs given actions/assertion on first child in lazy list
     *
     * @param T Type of item at first position. Must be registered via constructor.
     * @param function Tail lambda which receiver will be matched item with given type T
     */
    @ExperimentalTestApi
    inline fun <reified T : KLazyListItemNode> firstChild(function: T.() -> Unit) {
        childAt(0, function)
    }
}
