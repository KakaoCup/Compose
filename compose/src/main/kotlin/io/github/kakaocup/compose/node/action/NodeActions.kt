package io.github.kakaocup.compose.node.action

import androidx.compose.ui.semantics.AccessibilityAction
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsActions.ScrollBy
import androidx.compose.ui.semantics.SemanticsActions.ScrollToIndex
import androidx.compose.ui.semantics.SemanticsProperties.HorizontalScrollAxisRange
import androidx.compose.ui.semantics.SemanticsProperties.IndexForKey
import androidx.compose.ui.semantics.SemanticsProperties.VerticalScrollAxisRange
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.*
import io.github.kakaocup.compose.intercept.delegate.ComposeDelegate
import io.github.kakaocup.compose.intercept.operation.ComposeOperationType
import io.github.kakaocup.compose.node.action.extension.createOffset
import io.github.kakaocup.compose.node.action.option.ClickConfig
import io.github.kakaocup.compose.node.action.option.DoubleClickConfig
import io.github.kakaocup.compose.node.action.option.LongClickConfig
import io.github.kakaocup.compose.node.action.option.Offsets

interface NodeActions {
    val delegate: ComposeDelegate

    /**
     * Performs a click action on the element represented by the given semantics node.
     */
    fun performClick(offset: Offsets = Offsets.CENTER, config: ClickConfig? = null) {
        delegate.perform(ComposeBaseActionType.PERFORM_CLICK) {
            performTouchInput {
                click(position = createOffset(offset, config?.xOffset, config?.yOffset))
            }
        }
    }

    /**
     * Performs a double click action on the element represented by the given semantics node.
     */
    fun performDoubleClick(offset: Offsets = Offsets.CENTER, config: DoubleClickConfig? = null) {
        delegate.perform(ComposeBaseActionType.PERFORM_DOUBLE_CLICK) {
            performTouchInput {
                doubleClick(
                    delayMillis = config?.delayMs
                        ?: ((viewConfiguration.doubleTapMinTimeMillis + viewConfiguration.doubleTapTimeoutMillis) / 2),
                    position = createOffset(offset, config?.xOffset, config?.yOffset)
                )
            }
        }
    }

    /**
     * Performs a long click action on the element represented by the given semantics node.
     */
    fun performLongClick(offset: Offsets = Offsets.CENTER, config: LongClickConfig? = null) {
        delegate.perform(ComposeBaseActionType.PERFORM_LONG_CLICK) {
            performTouchInput {
                longClick(
                    position = createOffset(offset, config?.xOffset, config?.yOffset),
                    durationMillis = config?.durationMs ?: viewConfiguration.longPressTimeoutMillis
                )
            }
        }
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
        delegate.perform(ComposeBaseActionType.PERFORM_SCROLL_TO) { performScrollTo() }
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
        delegate.perform(ComposeBaseActionType.PERFORM_SCROLL_TO_INDEX) { performScrollToIndex(index) }
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
        delegate.perform(ComposeBaseActionType.PERFORM_SCROLL_TO_KEY) { performScrollToKey(key) }
    }

    /**
     * Scrolls a scrollable container to the content that matches the given [matcher]. If the content
     * isn't yet visible, the scrollable container will be scrolled from the start till the end till
     * it finds the content we're looking for. It is not defined where in the viewport the content
     * will be on success of this function, but it will be either fully within the viewport if it is
     * smaller than the viewport, or it will cover the whole viewport if it is larger than the
     * viewport. If it doesn't find the content, the scrollable will be left at the end of the
     * content and an [AssertionError] is thrown.
     *
     * This action should be performed on a [node][SemanticsNodeInteraction] that is a scrollable
     * container, not on a node that is part of the content of that container. If the container is a
     * lazy container, it must support the semantics actions [ScrollToIndex], [ScrollBy], and either
     * [HorizontalScrollAxisRange] or [VerticalScrollAxisRange], for example
     * [LazyColumn][androidx.compose.foundation.lazy.LazyColumn] and
     * [LazyRow][androidx.compose.foundation.lazy.LazyRow]. If the container is not lazy, it must
     * support the semantics action [ScrollBy], for example,
     * [Row][androidx.compose.foundation.layout.Row] or
     * [Column][androidx.compose.foundation.layout.Column].
     *
     * Throws an [AssertionError] if the scrollable node doesn't support the necessary semantics
     * actions.
     *
     * @param matcher A matcher that identifies the content where the scrollable container needs to
     * scroll to
     * @return The [SemanticsNodeInteraction] that is the receiver of this method. Note that this is
     * _not_ an interaction for the node that is identified by the [matcher].
     *
     * @see hasScrollToNodeAction
     */
    @ExperimentalTestApi
    fun performScrollToNode(matcher: SemanticsMatcher) {
        delegate.perform(ComposeBaseActionType.PERFORM_SCROLL_TO_NODE) { performScrollToNode(matcher) }
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
    @Deprecated(
        message = "Replaced by performTouchInput",
        replaceWith = ReplaceWith(
            "performTouchInput(block)",
            "import io.github.kakaocup.compose.node.action.performTouchInput"
        )
    )
    fun performGesture(
        block: GestureScope.() -> Unit
    ) {
        delegate.perform(ComposeBaseActionType.PERFORM_GESTURE) { performGesture(block) }
    }

    /**
     * Executes the touch gesture specified in the given [block]. The gesture doesn't need to be
     * complete and can be resumed in a later invocation of one of the `perform.*Input` methods. The
     * event time is initialized to the current time of the [MainTestClock].
     *
     * Be aware that if you split a gesture over multiple invocations of `perform.*Input`, everything
     * that happens in between will run as if the gesture is still ongoing (imagine a finger still
     * touching the screen).
     *
     * All events that are injected from the [block] are batched together and sent after [block] is
     * complete. This method blocks while the events are injected. If an error occurs during
     * execution of [block] or injection of the events, all (subsequent) events are dropped and the
     * error is thrown here.
     *
     * Due to the batching of events, all events in a block are sent together and no recomposition will
     * take place in between events. Additionally all events will be generated before any of the events
     * take effect. This means that the screen coordinates of all events are resolved before any of
     * the events can cause the position of the node being injected into to change. This has certain
     * advantages, for example, in the cases of nested scrolling or dragging an element around, it
     * prevents the injection of events into a moving target since all events are enqueued before any
     * of them has taken effect.
     *
     * Example of performing a swipe up:
     * ```
     * onNodeWithTag("myComponent")
     *     .performTouchInput { swipeUp() }
     * ```
     * Example of performing an off-center click:
     * ```
     * onNodeWithTag("myComponent")
     *     .performTouchInput { click(percentOffset(.2f, .5f)) }
     * ```
     * Example of doing an assertion during a click:
     * ```
     * onNodeWithTag("myComponent")
     *     .performTouchInput { down(topLeft) }
     *     .assertHasClickAction()
     *     .performTouchInput { up() }
     * ```
     * Example of performing a click-and-drag:
     * ```
     * onNodeWithTag("myComponent").performTouchInput {
     *     click()
     *     advanceEventTime(100)
     *     swipeUp()
     * }
     * ```
     *
     * @param block A lambda with [TouchInjectionScope] as receiver that describes the gesture by
     * sending all touch events.
     * @return The [SemanticsNodeInteraction] that is the receiver of this method
     *
     * @see TouchInjectionScope
     */
    fun performTouchInput(
        block: TouchInjectionScope.() -> Unit
    ) {
        delegate.perform(ComposeBaseActionType.PERFORM_TOUCH_INPUT) { performTouchInput(block) }
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
        delegate.perform(ComposeBaseActionType.PERFORM_SEMANTICS_ACTION) { performSemanticsAction(key, invocation) }
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
        delegate.perform(ComposeBaseActionType.PERFORM_SEMANTICS_ACTION) { performSemanticsAction(key) }
    }

    enum class ComposeBaseActionType : ComposeOperationType {
        PERFORM_CLICK,
        PERFORM_DOUBLE_CLICK,
        PERFORM_LONG_CLICK,
        PERFORM_SCROLL_TO,
        PERFORM_SCROLL_TO_INDEX,
        PERFORM_SCROLL_TO_KEY,
        PERFORM_SCROLL_TO_NODE,
        PERFORM_GESTURE,
        PERFORM_TOUCH_INPUT,
        PERFORM_SEMANTICS_ACTION,
    }
}
