package io.github.kakaocup.compose.intercept.base

import androidx.test.espresso.ViewInteraction
import io.github.kakaocup.compose.intercept.base.Interceptor.Builder
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

// todo change the description
/**
 * Base class for intercepting the call chain from Kakao-Compose to Compose.
 *
 * Interceptors can be provided through [KakaoCompose][io.github.kakaocup.compose.KakaoCompose] runtime,
 * different [Screens][io.github.kakaocup.kakao.screen.Screen] as well as [KViews][io.github.kakaocup.kakao.common.views.KBaseView].
 *
 * Interceptors are stacked during the runtime for any Kakao-Espresso `check` and `perform` operations.
 * The stack ordering is following: KView interceptor -> Screen interceptors -> Kakao interceptor.
 *
 * Any of the interceptors in the chain can break the chain call by setting `isOverride` to true
 * in [onCheck][Builder.onCheck], [onPerform][Builder.onPerform] or [onAll][Builder.onAll] interception
 * functions during the configuration. Doing this will not only prevent underlying
 * interceptors from being invoked, but prevents Kakao from executing the operation. In that case,
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
 * @see io.github.kakaocup.kakao.Kakao
 * @see io.github.kakaocup.kakao.screen.Screen
 * @see io.github.kakaocup.kakao.common.views.KBaseView
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

        internal fun build(): Interceptor<INTERACTION, ASSERTION, ACTION> = Interceptor(onCheck, onPerform, onAll)
    }

    /**
     * Configuration class that is used for building interceptors on the
     * [Kakao][io.github.kakaocup.kakao.Kakao] runtime and [Screen][io.github.kakaocup.kakao.screen.Screen] levels.
     *
     * @see io.github.kakaocup.kakao.Kakao
     * @see io.github.kakaocup.kakao.screen.Screen
     */
    class Configurator {
        private var composeInterceptor: Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>? = null

        /**
         * Setups the interceptor for `check` and `perform` operations happening through [ViewInteraction]
         *
         * @param builder Builder of interceptor for [ViewInteraction]
         */
        fun onComposeInteraction(builder: Builder<ComposeInteraction, ComposeAssertion, ComposeAction>.() -> Unit) {
            composeInterceptor = Builder<ComposeInteraction, ComposeAssertion, ComposeAction>().apply(builder).build()
        }

        internal fun configure() = Configuration(
            composeInterceptor
        )
    }

    data class Configuration(
        val composeInterceptor: Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>?,
    )
}
