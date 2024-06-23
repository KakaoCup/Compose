package io.github.kakaocup.compose.node.element.list

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.onChildren
import io.github.kakaocup.compose.KakaoCompose
import io.github.kakaocup.compose.node.action.NodeActions.ComposeBaseActionType
import io.github.kakaocup.compose.node.assertion.ListNodeAssertions
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.node.element.KNode

/**
 * A slightly modified copy of [io.github.kakaocup.compose.node.element.lazylist.KLazyListNode].
 *
 * [KListNode] is intended for testing any lists.
 *
 * It can be used with both lazy and non-lazy containers containing any elements.
 *
 * - [androidx.compose.foundation.lazy.LazyColumn].
 * - [androidx.compose.foundation.lazy.LazyRow].
 * - [androidx.compose.foundation.layout.Row] + Modifier.[androidx.compose.foundation.horizontalScroll] (or without it).
 * - [androidx.compose.foundation.layout.Column] + Modifier.[androidx.compose.foundation.verticalScroll] (or without it).
 *
 * @param semanticsProvider ComposeRule for finding and interacting with semantic tree nodes.
 * @param nodeMatcher Node matcher.
 * @param parentNode Parent node (if any).
 * @param useUnmergedTree If true, use the unmerged semantic tree; otherwise, use the merged tree.
 * @param isScrollable If true, the [KListNode] is considered scrollable and allows scrolling by default
 * when searching for individual items. If false, no scrolling will occur. By default, it is assumed that the list can
 * scroll itself.
 * @param itemIndexSemanticsPropertyKey Semantic property key for the list item index.
 * @param lengthSemanticsPropertyKey Semantic property key for the list length.
 */
class KListNode(
    semanticsProvider: SemanticsNodeInteractionsProvider,
    nodeMatcher: NodeMatcher,
    parentNode: BaseNode<*>?,
    val useUnmergedTree: Boolean = KakaoCompose.Override.useUnmergedTree ?: false,
    val isScrollable: Boolean = true,
    val itemIndexSemanticsPropertyKey: SemanticsPropertyKey<Int>?,
    override val lengthSemanticsPropertyKey: SemanticsPropertyKey<Int>,
) : BaseNode<KListNode>(semanticsProvider, nodeMatcher, parentNode),
    ListNodeAssertions {

    companion object {
        val itemIndexPropertyErrorMessage = """
                |[itemIndexSemanticsPropertyKey] parameter is not specified for KListNode 
                |Configure it by using [KListNode(.., itemIndexSemanticsPropertyKey = ListItemIndexPropertyKey)]"""
            .trimMargin()
    }

    val rootNodeMatcher = nodeMatcher.matcher

    /**
     * Constructor for creating [KListNode] using a convenient [ViewBuilder].
     *
     * @param semanticsProvider ComposeRule for finding and interacting with semantic tree nodes.
     * @param viewBuilderAction Lambda for building the node matcher using [ViewBuilder].
     * @param parentNode Parent node (if any).
     * @param useUnmergedTree If true, use the unmerged semantic tree; otherwise, use the merged tree.
     * @param isScrollable If true, the [KListNode] is considered scrollable and allows scrolling by default
     * when searching for individual items. If false, no scrolling will occur. By default, it is assumed that the list can
     * scroll itself.
     * @param itemIndexSemanticsPropertyKey Semantic property key for the list item index.
     * @param lengthSemanticsPropertyKey Semantic property key for the list length.
     */
    @Suppress("detekt.LongParameterList")
    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        viewBuilderAction: ViewBuilder.() -> Unit,
        parentNode: BaseNode<*>?,
        useUnmergedTree: Boolean,
        isScrollable: Boolean,
        itemIndexSemanticsPropertyKey: SemanticsPropertyKey<Int>?,
        lengthSemanticsPropertyKey: SemanticsPropertyKey<Int>,
    ) : this(
        semanticsProvider = semanticsProvider,
        nodeMatcher = ViewBuilder().apply(viewBuilderAction).build(),
        parentNode = parentNode,
        useUnmergedTree = useUnmergedTree,
        isScrollable = isScrollable,
        itemIndexSemanticsPropertyKey = itemIndexSemanticsPropertyKey,
        lengthSemanticsPropertyKey = lengthSemanticsPropertyKey,
    )

    // region Methods for checking the existence / non-existence of items in the list

    /**
     * Checks that the item at the specified position is displayed.
     *
     * @param index Position of the item.
     *
     * @throws NullPointerException if [itemIndexSemanticsPropertyKey] is `null`.
     * @throws [AssertionError] if the node cannot be found in the list.
     */
    @OptIn(ExperimentalTestApi::class)
    fun assertItemIsDisplayedAt(index: Int) {
        val childMatcher = buildChildMatcher(index)

        if (isScrollable) {
            delegate.perform(ComposeBaseActionType.PERFORM_SCROLL_TO_NODE) {
                performScrollToNode(childMatcher.matcher)
            }
        } else {
            requireSemanticsProvider()
                .onNode(rootNodeMatcher, this.useUnmergedTree)
                .onChildren()
                .filter(childMatcher.matcher)[childMatcher.position]
                .assertIsDisplayed()
        }
    }

    /**
     * Checks that the item at the specified position is not displayed.
     *
     * @param index Position of the item.
     *
     * @throws NullPointerException if [itemIndexSemanticsPropertyKey] is `null`.
     * @throws [AssertionError] if the node can be found in the list.
     */
    @OptIn(ExperimentalTestApi::class)
    fun assertItemIsNotDisplayedAt(index: Int) {
        val childMatcher = buildChildMatcher(index)

        if (isScrollable) {
            delegate.perform(ComposeBaseActionType.PERFORM_SCROLL_TO_NODE) {
                try {
                    performScrollToNode(childMatcher.matcher)
                    error("Found node that matches ${childMatcher.matcher.description}")
                } catch (ex: IllegalStateException) {
                    // If we are at this point, it means we found a node that should not exist.
                    throw ex
                } catch (ignored: Throwable) {
                    // If we are at this point -> everything is fine.
                }
            }
        } else {
            requireSemanticsProvider()
                .onNode(rootNodeMatcher, this.useUnmergedTree)
                .onChildren()
                .filter(childMatcher.matcher)[childMatcher.position]
                .assertIsNotDisplayed()
        }
    }

    /**
     * Checks that the item found using the Matcher exists in the list.
     *
     * @param viewBuilderAction Builder for creating the search matcher.
     *
     * @throws [AssertionError] if the node cannot be found in the list.
     */
    @OptIn(ExperimentalTestApi::class)
    fun assertItemWithIsDisplayed(viewBuilderAction: ViewBuilder.() -> Unit) {
        val childMatcher = buildChildMatcher(viewBuilderAction)

        if (isScrollable) {
            delegate.perform(ComposeBaseActionType.PERFORM_SCROLL_TO_NODE) {
                performScrollToNode(childMatcher.matcher)
            }
        } else {
            val childNode = this.child<KNode>(viewBuilderAction)
            childNode.assertIsDisplayed()
        }
    }

    /**
     * Checks that the item found using the Matcher does not exist in the list.
     *
     * @param viewBuilderAction Builder for creating the search matcher.
     *
     * @throws [AssertionError] if the node can be found in the list.
     */
    @OptIn(ExperimentalTestApi::class)
    fun assertItemWithIsNotDisplayed(viewBuilderAction: ViewBuilder.() -> Unit) {
        val childMatcher = buildChildMatcher(viewBuilderAction)

        if (isScrollable) {
            delegate.perform(ComposeBaseActionType.PERFORM_SCROLL_TO_NODE) {
                try {
                    performScrollToNode(childMatcher.matcher)
                    error("Found node that matches ${childMatcher.matcher.description}")
                } catch (ex: IllegalStateException) {
                    // If we are at this point, it means we found a node that should not exist.
                    throw ex
                } catch (ignored: Throwable) {
                    // If we are at this point -> everything is fine.
                }
            }
        } else {
            val childNode = this.child<KNode>(viewBuilderAction)
            childNode.assertIsNotDisplayed()
        }
    }

    // endregion

    // region Methods for working with items at a position

    /**
     * Performs the specified actions / checks on the list item at the specified position.
     *
     * This method should be used if you DO NOT NEED a specific type of list item node.
     *
     * @param index Index of the item in the list node.
     * @param needPerformScroll If true, additional scrolling will be performed to the specified position.
     * By default, scrolling depends on the [isScrollable] flag.
     * @param function Function for processing the item.
     *
     * @throws NullPointerException if [itemIndexSemanticsPropertyKey] is `null`.
     */
    fun itemAt(
        index: Int,
        needPerformScroll: Boolean = isScrollable,
        function: KListItemNode<*>.() -> Unit,
    ) {
        childAt<KListItemNode<*>>(index, needPerformScroll, function)
    }

    /**
     * Performs the specified actions / checks on the list item at the specified position.
     *
     * This method should be used if you NEED a specific type of list item node.
     *
     * @param T Specific type of list item.
     * @param index Index of the item in the list node.
     * @param needPerformScroll If true, additional scrolling will be performed to the specified position.
     * By default, scrolling depends on the [isScrollable] flag.
     * @param function Function for processing the item.
     *
     * @throws NullPointerException if [itemIndexSemanticsPropertyKey] is `null`.
     */
    inline fun <reified T : KListItemNode<*>> childAt(
        index: Int,
        needPerformScroll: Boolean = isScrollable,
        function: T.() -> Unit,
    ) {
        val childItem = getChildAt<T>(index, needPerformScroll = needPerformScroll)
        function.invoke(childItem)
    }

    /**
     * Performs the specified actions / checks on the list item at the first position.
     *
     * This method should be used if you DO NOT NEED a specific type of list item node.
     *
     * @param needPerformScroll If true, additional scrolling will be performed to the first position.
     * By default, scrolling depends on the [isScrollable] flag.
     * @param function Function for processing the item.
     */
    fun firstItem(
        needPerformScroll: Boolean = isScrollable,
        function: KListItemNode<*>.() -> Unit,
    ) {
        firstChild(needPerformScroll, function)
    }

    /**
     * Performs the specified actions / checks on the list item at the first position.
     *
     * This method should be used if you NEED a specific type of list item node.
     *
     * @param T Specific type of list item.
     * @param needPerformScroll If true, additional scrolling will be performed to the first position.
     * By default, scrolling depends on the [isScrollable] flag.
     * @param function Function for processing the item.
     */
    inline fun <reified T : KListItemNode<*>> firstChild(
        needPerformScroll: Boolean = isScrollable,
        function: T.() -> Unit,
    ) {
        childAt<T>(0, needPerformScroll, function)
    }

    // endregion

    // region Methods for working with items by Matcher

    /**
     * Finds a child element of the list node using [ViewBuilder] and performs [function].
     *
     * This method should be used if you DO NOT NEED a specific type of list item node.
     *
     * @param viewBuilderAction Builder for creating the search matcher.
     * @param needPerformScroll If true, additional scrolling will be performed to the element with
     * the built [SemanticsMatcher]. By default, scrolling depends on the [isScrollable] flag.
     * @param function Function for processing the item.
     */
    fun itemWith(
        viewBuilderAction: ViewBuilder.() -> Unit,
        needPerformScroll: Boolean = isScrollable,
        function: KListItemNode<*>.() -> Unit,
    ) {
        val item = getItemWith(viewBuilderAction, needPerformScroll)
        function.invoke(item)
    }

    /**
     * Finds a child element of the list node using [ViewBuilder] and performs [function].
     *
     * This method should be used if you NEED a specific type of list item node.
     *
     * @param T Specific type of list item.
     * @param viewBuilderAction Builder for creating the search matcher.
     * @param needPerformScroll If true, additional scrolling will be performed to the element with
     * the built [SemanticsMatcher]. By default, scrolling depends on the [isScrollable] flag.
     * @param function Function for processing the item.
     */
    inline fun <reified T : KListItemNode<*>> childWith(
        noinline viewBuilderAction: ViewBuilder.() -> Unit,
        needPerformScroll: Boolean = isScrollable,
        function: T.() -> Unit,
    ) {
        val item = getChildWith<T>(viewBuilderAction, needPerformScroll)
        function.invoke(item)
    }

    // endregion

    // region Getters for list items

    /**
     * Returns a child element of the list node found using [ViewBuilder].
     *
     * This method should be used if you DO NOT NEED a specific type of list item node.
     *
     * @param viewBuilderAction Builder for creating the search matcher.
     * @param needPerformScroll If true, additional scrolling will be performed to the element with
     * the built [SemanticsMatcher]. By default, scrolling depends on the [isScrollable] flag.
     */
    fun getItemWith(
        viewBuilderAction: ViewBuilder.() -> Unit,
        needPerformScroll: Boolean = isScrollable,
    ): KListItemNode<*> =
        getChildWith(viewBuilderAction, needPerformScroll)

    /**
     * Returns a child element of the list node found using [ViewBuilder].
     *
     * This method should be used if you NEED a specific type of list item node.
     *
     * @param T Specific type of list item.
     * @param viewBuilderAction Builder for creating the search matcher.
     * @param needPerformScroll If true, additional scrolling will be performed to the element with
     * the built [SemanticsMatcher]. By default, scrolling depends on the [isScrollable] flag.
     */
    @OptIn(ExperimentalTestApi::class)
    inline fun <reified T : KListItemNode<*>> getChildWith(
        noinline viewBuilderAction: ViewBuilder.() -> Unit,
        needPerformScroll: Boolean = isScrollable,
    ): T {
        val childMatcher = buildChildMatcher(viewBuilderAction)

        if (needPerformScroll) {
            performScrollToNode(childMatcher.matcher)
        }

        val semanticsNode = requireSemanticsProvider()
            .onNode(rootNodeMatcher, this.useUnmergedTree)
            .onChildren()
            .filter(childMatcher.matcher)[childMatcher.position]
            .fetchSemanticsNode()

        return createListItemNode(semanticsNode)
    }

    /**
     * Returns a child element of the list node at the specified position.
     * Depending on the [needPerformScroll] parameter, additional scrolling may be performed to the element at
     * the specified position.
     *
     * This method should be used if you DO NOT NEED a specific type of list item node.
     *
     * @param index Index of the item in the list node.
     * @param needPerformScroll If true, we will try to scroll to the specified position.
     *
     * @throws NullPointerException if [needPerformScroll] is true and [itemIndexSemanticsPropertyKey] is `null`.
     */
    fun getItemAt(index: Int, needPerformScroll: Boolean = isScrollable): KListItemNode<*> =
        getChildAt(index, needPerformScroll)

    /**
     * Returns a child element of the list node at the specified position.
     * Depending on the [needPerformScroll] parameter, additional scrolling may be performed to the element at
     * the specified position.
     *
     * This method should be used if you NEED a specific type of list item node.
     *
     * @param T Specific type of element.
     * @param index Index of the item in the list node.
     * @param needPerformScroll If true, we will try to scroll to the specified position.
     *
     * @throws NullPointerException if [needPerformScroll] is true and [itemIndexSemanticsPropertyKey] is `null`.
     */
    @OptIn(ExperimentalTestApi::class)
    inline fun <reified T : KListItemNode<*>> getChildAt(index: Int, needPerformScroll: Boolean = isScrollable): T {
        val childMatcher = buildChildMatcher(index)

        if (needPerformScroll) {
            performScrollToNode(childMatcher.matcher)
        }

        // Warning!
        // Within lazy collections, `filterToOne` cannot be used on child nodes.
        // In Compose version 1.5.5, the semantic tree of lazy collections MAY CONTAIN DUPLICATES.
        val semanticsNode = requireSemanticsProvider()
            .onNode(rootNodeMatcher, this.useUnmergedTree)
            .onChildren()
            .filter(childMatcher.matcher)[childMatcher.position]
            .fetchSemanticsNode()

        return createListItemNode<T>(semanticsNode)
    }

    // endregion

    inline fun <reified T : KListItemNode<*>> createListItemNode(semanticsNode: SemanticsNode): T {
        return KListItemNode.newInstance(
            listNode = this,
            semanticsNode = semanticsNode,
            useUnmergedTree = useUnmergedTree,
        )
    }

    fun buildChildMatcher(viewBuilderAction: ViewBuilder.() -> Unit): NodeMatcher {
        return ViewBuilder()
            .apply {
                this.useUnmergedTree = this@KListNode.useUnmergedTree
            }
            .apply(viewBuilderAction)
            .build()
    }

    fun buildChildMatcher(index: Int): NodeMatcher {
        requireNotNull(itemIndexSemanticsPropertyKey) { itemIndexPropertyErrorMessage }

        return ViewBuilder()
            .apply {
                this.useUnmergedTree = this@KListNode.useUnmergedTree
                addSemanticsMatcher(
                    SemanticsMatcher.expectValue(this@KListNode.itemIndexSemanticsPropertyKey, index)
                )
            }
            .build()
    }

}
