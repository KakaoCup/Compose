package io.github.kakaocup.compose

import io.github.kakaocup.compose.intercept.base.Interceptor
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion
import io.github.kakaocup.compose.node.core.BaseNode

object KakaoCompose {
    internal var composeInterceptor: Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>? = null

    /**
     * Operator that allows usage of DSL style
     *
     * @param function Tail lambda with receiver which is your Kakao-Compose runtime
     */
    operator fun invoke(function: KakaoCompose.() -> Unit) {
        function(this)
    }

    /**
     * Sets the interceptors for the whole Kakao-Compose runtime.
     * Interceptors will be invoked on all of the interactions with the BaseNode instances.
     *
     * @param configurator Configuration of the interceptors
     *
     * @see Interceptor
     */
    fun intercept(configurator: Interceptor.Configurator.() -> Unit) {
        Interceptor.Configurator().apply(configurator).configure().also { (composeInterceptor) ->
            this.composeInterceptor = composeInterceptor
        }
    }

    /**
     * Removes the interceptors from the Kakao-Compose runtime.
     *
     * @see intercept
     * @see Interceptor
     */
    fun reset() {
        composeInterceptor = null
        BaseNode.composeInterceptors.clear()
    }
}
