package io.github.kakaocup.compose.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
@Suppress("UNCHECKED_CAST")
open class ComposeScreen<out T : ComposeScreen<T>>
    (val composeTestRule: AndroidComposeTestRule<*, *>) {

    /**
     * Sets the given composable as a content of the current screen.
     * It also sets the value to the [MorsaScreen.context] to be used without the
     * need of a composable scope.
     *
     * Use this in your tests to setup the UI content to be tested. This should be called exactly
     * once per test.
     *
     * @throws IllegalStateException if called more than once per test.
     */
    fun setContent(composable: @Composable () -> Unit) = composeTestRule.setContent(composable)

    /**
     * Runs the given action on the UI thread.
     *
     * This method is blocking until the action is complete.
     */
    fun <T> runOnUiThread(action: () -> T): T = composeTestRule.runOnUiThread(action)

    /**
     * Executes the given action in the same way as [runOnUiThread] but also makes sure Compose
     * is idle before executing it. This is great place for doing your assertions on shared
     * variables.
     *
     * This method is blocking until the action is complete.
     *
     * In case the main clock auto advancement is enabled (by default is) this will also keep
     * advancing the clock until it is idle (meaning there are no recompositions, animations, etc.
     * pending). If not, this will wait only for other idling resources.
     */
    fun <T> runOnIdle(action: () -> T): T = composeTestRule.runOnIdle(action)

    /**
     * Waits for compose to be idle.
     *
     * This is a blocking call. Returns only after compose is idle.
     *
     * In case the main clock auto advancement is enabled (by default is) this will also keep
     * advancing the clock until it is idle (meaning there are no recompositions, animations, etc.
     * pending). If not, this will wait only for other idling resources.
     *
     * Can crash in case there is a time out. This is not supposed to be handled as it
     * surfaces only in incorrect tests.
     */
    fun waitForIdle() = composeTestRule.waitForIdle()

    /**
     * Suspends until compose is idle. Compose is idle if there are no pending compositions, no
     * pending changes that could lead to another composition, and no pending draw calls.
     *
     * In case the main clock auto advancement is enabled (by default is) this will also keep
     * advancing the clock until it is idle (meaning there are no recompositions, animations, etc.
     * pending). If not, this will wait only for other idling resources.
     */
    fun awaitIdle() = composeTestRule.waitForIdle()

    /**
     * Blocks until the given condition is satisfied.
     *
     * In case the main clock auto advancement is enabled (by default is), this will also keep
     * advancing the clock on a frame by frame basis and yield for other async work at the end of
     * each frame. If the advancement of the main clock is not enabled this will work as a
     * countdown latch without any other advancements.
     *
     * There is also [MainTestClock.advanceTimeUntil] which is faster as it does not yield back
     * the UI thread.
     *
     * This method should be used in cases where [MainTestClock.advanceTimeUntil]
     * is not enough.
     *
     * @param timeoutMillis The time after which this method throws an exception if the given
     * condition is not satisfied. This is the wall clock time not the main clock one.
     * @param condition Condition that must be satisfied in order for this method to successfully
     * finish.
     *
     * @throws ComposeTimeoutException If the condition is not satisfied after [timeoutMillis].
     */
    fun waitUntil(timeoutMillis: Long = 1_000, condition: () -> Boolean) {
        composeTestRule.waitUntil(timeoutMillis, condition)
    }

    operator fun invoke(function: T.() -> Unit) {
        function.invoke(this as T)
    }

    companion object {
        inline fun <reified T : ComposeScreen<T>> onComposeScreen(
            composeTestRule: AndroidComposeTestRule<*, *>,
            noinline function: T.() -> Unit
        ): T {
            return T::class.java
                .getDeclaredConstructor(AndroidComposeTestRule::class.java)
                .newInstance(composeTestRule)
                .apply { this(function) }
        }
    }
}