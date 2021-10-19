package io.github.kakaocup.compose.node

import androidx.compose.ui.semantics.AccessibilityAction
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsActions.ScrollToIndex
import androidx.compose.ui.semantics.SemanticsProperties.IndexForKey
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.*
import io.github.kakaocup.compose.intercept.delegate.ComposeDelegate

interface NodeActions {
    val node: ComposeDelegate


    /**
     * Performs a click action on the element represented by the given semantics node.
     */
    fun performClick() {
        node.perform { it.performClick() }
    }

    /**
     * Scrolls the closest enclosing scroll parent by the smallest amount such that this node is fully
     * visible in its viewport. If this node is larger than the viewport, scrolls the scroll parent
     * by the smallest amount such that this node fills the entire viewport. A scroll parent is a
     * parent node that has the semantics action [SemanticsActions.ScrollBy] (usually implemented by
     * defining [scrollBy][androidx.compose.ui.semantics.scrollBy]).
     *
     * This action should be performed on the [node][SemanticsNodeInteraction] that is part of the
     * scrollable content, not on the scrollable container.
     *
     * Throws an [AssertionError] if there is no scroll parent.
     */
    fun performScrollTo() {
        node.perform { it.performScrollTo() }
    }

    /**
     * Scrolls a scrollable container with items to the item with the given [index].
     *
     * Note that not all scrollable containers have item indices. For example, a
     * [scrollable][androidx.compose.foundation.gestures.scrollable] doesn't have items with an
     * index, while [LazyColumn][androidx.compose.foundation.lazy.LazyColumn] does.
     *
     * This action should be performed on a [node][SemanticsNodeInteraction] that is a scrollable
     * container, not on a node that is part of the content of that container.
     *
     * Throws an [AssertionError] if the node doesn't have [ScrollToIndex] defined.
     *
     * @param index The index of the item to scroll to
     * @see hasScrollToIndexAction
     */
    @ExperimentalTestApi
    fun performScrollToIndex(index: Int) {
        node.perform { it.performScrollToIndex(index) }
    }

    /**
     * Scrolls a scrollable container with keyed items to the item with the given [key], such as
     * [LazyColumn][androidx.compose.foundation.lazy.LazyColumn] or
     * [LazyRow][androidx.compose.foundation.lazy.LazyRow].
     *
     * This action should be performed on a [node][SemanticsNodeInteraction] that is a scrollable
     * container, not on a node that is part of the content of that container.
     *
     * Throws an [AssertionError] if the node doesn't have [IndexForKey] or [ScrollToIndex] defined.
     *
     * @param key The key of the item to scroll to
     * @see hasScrollToKeyAction
     */
    @ExperimentalTestApi
    fun performScrollToKey(key: Any) {
        node.perform { it.performScrollToKey(key) }
    }

    /**
     * Executes the (partial) gesture specified in the given [block]. The gesture doesn't need to be
     * complete and can be resumed in a later invocation of [performGesture]. It is the
     * responsibility of the caller to make sure partial gestures don't leave the test in an
     * inconsistent state.
     *
     * All events that are injected from the [block] are batched together and sent after [block] is
     * complete. This method blocks until all those events have been injected, which normally takes
     * as long as the duration of the gesture. If an error occurs during execution of [block] or
     * injection of the events, all (subsequent) events are dropped and the error is thrown here.
     *
     * This method must not be called from the main thread. The block will be executed on the same
     * thread as the caller.
     *
     * Example usage:
     * ```
     * onNodeWithTag("myWidget")
     *     .performGesture { swipeUp() }
     *
     * onNodeWithTag("myWidget")
     *     .performGesture { click(center) }
     *
     * onNodeWithTag("myWidget")
     *     .performGesture { down(topLeft) }
     *     .assertHasClickAction()
     *     .performGesture { up(topLeft) }
     * ```
     */
    fun performGesture(
        block: GestureScope.() -> Unit
    ) {
        node.perform { it.performGesture(block) }
    }

    /**
     * Provides support to call custom semantics actions on this node.
     *
     * This method is supposed to be used for actions with parameters.
     *
     * This will properly verify that the actions exists and provide clear error message in case it
     * does not. It also handle synchronization and performing the action on the UI thread. This call
     * is blocking until the action is performed
     *
     * @param key Key of the action to be performed.
     * @param invocation Place where you call your action. In the argument is provided the underlying
     * action from the given Semantics action.
     *
     * @throws AssertionError If the semantics action is not defined on this node.
     */
    fun <T : Function<Boolean>> performSemanticsAction(
        key: SemanticsPropertyKey<AccessibilityAction<T>>,
        invocation: (T) -> Unit
    ) {
        node.perform { it.performSemanticsAction(key, invocation) }
    }

    /**
     * Provides support to call custom semantics actions on this node.
     *
     * This method is for calling actions that have no parameters.
     *
     * This will properly verify that the actions exists and provide clear error message in case it
     * does not. It also handle synchronization and performing the action on the UI thread. This call
     * is blocking until the action is performed
     *
     * @param key Key of the action to be performed.
     *
     * @throws AssertionError If the semantics action is not defined on this node.
     */
    fun performSemanticsAction(
        key: SemanticsPropertyKey<AccessibilityAction<() -> Boolean>>
    ) {
        node.perform { it.performSemanticsAction(key) }
    }
}