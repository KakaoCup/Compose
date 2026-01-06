package io.github.kakaocup.appium

import io.appium.java_client.AppiumDriver
import io.github.kakaocup.appium.intercept.base.Interceptor
import io.github.kakaocup.appium.intercept.interaction.AppiumInteraction
import io.github.kakaocup.appium.intercept.operation.AppiumAction
import io.github.kakaocup.appium.intercept.operation.AppiumAssertion

object KakaoAppium {
    internal var appiumInterceptor: Interceptor<AppiumInteraction, AppiumAssertion, AppiumAction>? =
        null

    /**
     * Operator that allows usage of DSL style
     *
     * @param function Tail lambda with receiver which is your Kakao-Appium runtime
     */
    operator fun invoke(function: KakaoAppium.() -> Unit) {
        function(this)
    }

    /**
     * Sets the interceptors for the whole Kakao-Appium runtime.
     * Interceptors will be invoked on all of the interactions with the BaseNode instances.
     *
     * @param configurator Configuration of the interceptors
     *
     * @see Interceptor
     */
    fun intercept(configurator: Interceptor.Configurator.() -> Unit) {
        Interceptor.Configurator().apply(configurator).configure().also { (appiumInterceptor) ->
            this.appiumInterceptor = appiumInterceptor
        }
    }

    /**
     * Global parameters
     */

    object Global {
        /**
         * Global Appium Driver can be set via KakaoDriverTestRule
         * to avoid injection boilerplate into each AppiumScreen
         * @see KakaoAppiumTestRule
         */
        var driver: AppiumDriver? = null
    }

    fun reset() {
        appiumInterceptor = null
    }
}
