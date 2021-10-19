package io.github.kakaocup.compose.intercept.base

import io.github.kakaocup.compose.intercept.delegate.Delegate

interface Interceptable<INTERACTION, ASSERTION, ACTION> {
    val node: Delegate<INTERACTION, ASSERTION, ACTION>

    /**
     * Sets the interceptors for the instance.
     * Interceptors will be invoked on the interaction with the KView.
     *
     * @param builder Builder of the interceptors
     *
     * @see Interceptor
     */
    fun intercept(builder: Interceptor.Builder<INTERACTION, ASSERTION, ACTION>.() -> Unit) {
        node.interceptor = Interceptor.Builder<INTERACTION, ASSERTION, ACTION>().apply(builder).build()
    }

    /**
     * Removes the interceptors from the instance.
     */
    fun reset() {
        node.interceptor = null
    }
}
