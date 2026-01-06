package io.github.kakaocup.appium.intercept.base

import io.github.kakaocup.appium.intercept.interaction.AppiumInteraction
import io.github.kakaocup.appium.intercept.operation.AppiumAction
import io.github.kakaocup.appium.intercept.operation.AppiumAssertion

/**
 * Base class for intercepting the call chain from Kakao-Appium to Appium.
 *
 * Interceptors can be provided through [KakaoAppium][io.github.kakaocup.appium.KakaoAppium] runtime and
 * different [Nodes][io.github.kakaocup.appium.node.core.BaseNode].
 *
 * Interceptors are stacked during the runtime for any Kakao-Appium `check` and `perform` operations.
 * The stack ordering is following: current BaseNode interceptor -> current BaseNode parents' interceptors -> Kakao-Appium interceptor.
 *
 * Any of the interceptors in the chain can break the chain call by setting `isOverride` to true
 * in [onCheck][Builder.onCheck], [onPerform][Builder.onPerform] or [onAll][Builder.onAll] interception
 * functions during the configuration. Doing this will not only prevent underlying
 * interceptors from being invoked, but prevents Kakao-Appium from executing the operation. In that case,
 * responsibility for actually making Espresso call lies on developer.
 *
 * For each operation the interceptor invocation cycle will be as follows:
 * ```
 * // For check operation
 * onAll?.invoke()
 * onCheck?.invoke()
 *
 * // For perform operation
 * onAll?.invoke()
 * onPerform?.invoke()
 * ```
 *
 * @see io.github.kakaocup.appium.KakaoAppium
 * @see io.github.kakaocup.appium.node.core.BaseNode
 * @see io.github.kakaocup.appium.intercept.delegate.AppiumInterceptable
 */
class Interceptor<INTERACTION, ASSERTION, ACTION>(
    val onCheck: Interception<(INTERACTION, ASSERTION) -> Unit>?,
    val onPerform: Interception<(INTERACTION, ACTION) -> Unit>?,
    val onAll: Interception<(INTERACTION) -> Unit>?
) {
    /**
     * Builder class that is used to build a single instance of [Interceptor].
     *
     * @see Interceptor
     */
    class Builder<INTERACTION, ASSERTION, ACTION> {
        private var onCheck: Interception<(INTERACTION, ASSERTION) -> Unit>? = null
        private var onPerform: Interception<(INTERACTION, ACTION) -> Unit>? = null
        private var onAll: Interception<(INTERACTION) -> Unit>? = null

        /**
         * Sets the interceptor for the `check` operation for a given interaction.
         * If overridden, breaks the call chain of operation and transfers the responsibility
         * to invoke the Espresso on the developer.
         *
         * @param isOverride if `true` - breaks the call chain, false otherwise
         * @param interceptor lambda with intercepting logic
         */
        fun onCheck(isOverride: Boolean = false, interceptor: (INTERACTION, ASSERTION) -> Unit) {
            onCheck = Interception(isOverride, interceptor)
        }

        /**
         * Sets the interceptor for the `perform` operation for a given interaction.
         * If overridden, breaks the call chain of operation and transfers the responsibility
         * to invoke the Espresso on the developer.
         *
         * @param isOverride if `true` - breaks the call chain, false otherwise
         * @param interceptor lambda with intercepting logic
         */
        fun onPerform(isOverride: Boolean = false, interceptor: (INTERACTION, ACTION) -> Unit) {
            onPerform = Interception(isOverride, interceptor)
        }

        /**
         * Sets the interceptor for the `check` and `perform` operations for a given interaction.
         * If overridden, breaks the call chain of operation and transfers the responsibility
         * to invoke the Espresso on the developer.
         *
         * This interceptor is prioritized and is being invoked first for both operations.
         *
         * @param isOverride if `true` - breaks the call chain, false otherwise
         * @param interceptor lambda with intercepting logic
         */
        fun onAll(isOverride: Boolean = false, interceptor: (INTERACTION) -> Unit) {
            onAll = Interception(isOverride, interceptor)
        }

        internal fun build(): Interceptor<INTERACTION, ASSERTION, ACTION> =
            Interceptor(onCheck, onPerform, onAll)
    }

    /**
     * Configuration class that is used for building interceptors on the
     * [Kakao][io.github.kakaocup.kakao.Kakao] runtime and [Screen][io.github.kakaocup.kakao.screen.Screen] levels.
     *
     * @see io.github.kakaocup.kakao.Kakao
     * @see io.github.kakaocup.kakao.screen.Screen
     */
    class Configurator {
        private var appiumInterceptor: Interceptor<AppiumInteraction, AppiumAssertion, AppiumAction>? =
            null

        /**
         * Setups the interceptor for `check` and `perform` operations happening through [ViewInteraction]
         *
         * @param builder Builder of interceptor for [ViewInteraction]
         */
        fun onAppiumInteraction(builder: Builder<AppiumInteraction, AppiumAssertion, AppiumAction>.() -> Unit) {
            appiumInterceptor =
                Builder<AppiumInteraction, AppiumAssertion, AppiumAction>().apply(builder).build()
        }

        internal fun configure() = Configuration(
            appiumInterceptor
        )
    }

    data class Configuration(
        val composeInterceptor: Interceptor<AppiumInteraction, AppiumAssertion, AppiumAction>?,
    )
}
