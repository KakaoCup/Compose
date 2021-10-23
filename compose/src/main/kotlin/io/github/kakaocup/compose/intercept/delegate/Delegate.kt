package io.github.kakaocup.compose.intercept.delegate

import io.github.kakaocup.compose.intercept.base.Interceptor
import io.github.kakaocup.compose.intercept.interaction.Interaction

/**
 * Base delegate interface for Kakao-Compose.
 *
 * Provides functionality of aggregating interceptors and invoking them on `check`
 * and `perform` invocations.
 *
 * @see Interceptor
 */
interface Delegate<INTERACTION, ASSERTION, ACTION> where INTERACTION : Interaction<ASSERTION, ACTION> {
    val interaction: INTERACTION

    fun nodeInterceptors(): Iterable<Interceptor<INTERACTION, ASSERTION, ACTION>>
    fun globalInterceptor(): Interceptor<INTERACTION, ASSERTION, ACTION>?

    fun perform(action: ACTION) {
        if (!interceptPerform(action)) interaction.perform(action)
    }

    fun check(assertion: ASSERTION) {
        if (!interceptCheck(assertion)) interaction.check(assertion)
    }

    /**
     * Runs the interceptors available for the given delegate during the `check` operation.
     *
     * @return `true` if the call chain has been interrupted and there is no need to do UiAutomator call,
     *         false otherwise.
     */
    private fun interceptCheck(assertion: ASSERTION): Boolean {
        fun intercept(interceptor: Interceptor<INTERACTION, ASSERTION, ACTION>): Boolean {
            return interceptOnAll(interceptor) || interceptOnCheck(interceptor, assertion)
        }

        return nodeInterceptors().any { intercept(it) } ||
                globalInterceptor()?.let { intercept(it) } ?: false
    }

    /**
     * Runs the interceptors available for the given delegate during the `execute` operation.
     *
     * @return `true` if the call chain has been interrupted and there is no need to do UiAutomator call,
     *         false otherwise.
     */
    private fun interceptPerform(action: ACTION): Boolean {
        fun intercept(interceptor: Interceptor<INTERACTION, ASSERTION, ACTION>): Boolean {
            return interceptOnAll(interceptor) || interceptOnPerform(interceptor, action)
        }

        return nodeInterceptors().any { intercept(it) } ||
                globalInterceptor()?.let { intercept(it) } ?: false
    }

    private fun interceptOnAll(interceptor: Interceptor<INTERACTION, ASSERTION, ACTION>): Boolean {
        return interceptor.onAll?.let { (isOverride, interception) ->
            interception(interaction)
            isOverride
        } ?: false
    }

    private fun interceptOnCheck(interceptor: Interceptor<INTERACTION, ASSERTION, ACTION>, assertion: ASSERTION): Boolean {
        return interceptor.onCheck?.let { (isOverride, interception) ->
            interception(interaction, assertion)
            isOverride
        } ?: false
    }

    private fun interceptOnPerform(interceptor: Interceptor<INTERACTION, ASSERTION, ACTION>, action: ACTION): Boolean {
        return interceptor.onPerform?.let { (isOverride, interception) ->
            interception(interaction, action)
            isOverride
        } ?: false
    }
}
