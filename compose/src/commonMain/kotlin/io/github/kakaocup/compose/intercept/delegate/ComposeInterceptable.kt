package io.github.kakaocup.compose.intercept.delegate

import io.github.kakaocup.compose.intercept.base.Interceptor
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

interface ComposeInterceptable {

    val delegate: ComposeDelegate

    /**
     * Sets the interceptors for the instance.
     * Interceptors will be invoked on the interaction with the BaseNode.
     *
     * @param builder Builder of the interceptors
     *
     * @see Interceptor
     */
    fun intercept(builder: Interceptor.Builder<ComposeInteraction, ComposeAssertion, ComposeAction>.() -> Unit) {
        delegate.currentInterceptor = Interceptor.Builder<ComposeInteraction, ComposeAssertion, ComposeAction>()
            .apply(builder).build()
    }

    /**
     * Removes the interceptors from the instance.
     */
    fun reset() {
        delegate.currentInterceptor = null
    }
}
