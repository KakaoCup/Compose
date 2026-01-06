package io.github.kakaocup.appium.node.element

import io.appium.java_client.AppiumDriver
import io.github.kakaocup.appium.node.builder.ViewBuilder
import io.github.kakaocup.appium.node.core.AppiumMarker
import io.github.kakaocup.appium.node.core.BaseNode
import io.github.kakaocup.appium.utilities.checkNotNull
import io.github.kakaocup.appium.utilities.orGlobal

@Suppress("UNCHECKED_CAST")
@AppiumMarker
open class AppiumScreen<out T : AppiumScreen<T>> : BaseNode<T> {
    constructor(
        driver: AppiumDriver? = null,
    ) : super(driver)

    fun onWebElement(viewBuilderAction: ViewBuilder.() -> Unit) = KWebElement(
        driver.orGlobal().checkNotNull(),
        viewBuilderAction,
    )

    companion object {
        inline fun <reified T : AppiumScreen<T>> onAppiumScreen(
            diver: AppiumDriver,
            noinline function: T.() -> Unit,
        ): T = T::class.java
            .getDeclaredConstructor(
                AppiumDriver::class.java
            )
            .newInstance(diver)
            .apply { this(function) }

        inline fun <reified T : AppiumScreen<T>> onAppiumScreen(
            noinline function: T.() -> Unit,
        ): T =
            T::class.java.getDeclaredConstructor()
                .newInstance()
                .apply { this(function) }

    }
}
