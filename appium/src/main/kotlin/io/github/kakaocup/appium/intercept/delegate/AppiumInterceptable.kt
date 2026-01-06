package io.github.kakaocup.appium.intercept.delegate

import io.github.kakaocup.appium.intercept.base.Interceptor
import io.github.kakaocup.appium.intercept.interaction.AppiumInteraction
import io.github.kakaocup.appium.intercept.operation.AppiumAction
import io.github.kakaocup.appium.intercept.operation.AppiumAssertion

interface AppiumInterceptable {

    val delegate: WebElementDelegate

    /**
     * Sets the interceptors for the instance.
     * Interceptors will be invoked on the interaction with the BaseNode.
     *
     * @param builder Builder of the interceptors
     *
     * @see Interceptor
     */
    fun intercept(builder: Interceptor.Builder<AppiumInteraction, AppiumAssertion, AppiumAction>.() -> Unit) {
        delegate.currentInterceptor =
            Interceptor.Builder<AppiumInteraction, AppiumAssertion, AppiumAction>()
                .apply(builder).build()
    }

    /**
     * Removes the interceptors from the instance.
     */
    fun reset() {
        delegate.currentInterceptor = null
    }
}
