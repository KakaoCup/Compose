package io.github.kakaocup.appium.intercept.delegate

import io.github.kakaocup.appium.KakaoAppium
import io.github.kakaocup.appium.intercept.base.Interceptor
import io.github.kakaocup.appium.intercept.interaction.AppiumInteraction
import io.github.kakaocup.appium.intercept.operation.AppiumAction
import io.github.kakaocup.appium.intercept.operation.AppiumAssertion
import io.github.kakaocup.appium.intercept.operation.AppiumOperationType
import io.github.kakaocup.appium.intercept.operation.produceAppiumAction
import io.github.kakaocup.appium.intercept.operation.produceAppiumAssertion
import io.github.kakaocup.appium.node.builder.WebElementProvider
import org.openqa.selenium.WebElement

/**
 * Compose implementation of Base delegate interface for Kakao-Compose
 */
class WebElementDelegate(
    nodeProvider: WebElementProvider,
    private val parentDelegate: WebElementDelegate?,
) : Delegate<AppiumInteraction, AppiumAssertion, AppiumAction> {

    var currentInterceptor: Interceptor<AppiumInteraction, AppiumAssertion, AppiumAction>? = null

    override val interaction = AppiumInteraction(nodeProvider)

    override val nodeInterceptors: () -> Iterable<Interceptor<AppiumInteraction, AppiumAssertion, AppiumAction>> =
        {
            val currentList = currentInterceptor?.let { listOf(it) } ?: emptyList()
            val parentList = parentDelegate?.nodeInterceptors?.invoke() ?: emptyList()
            currentList + parentList
        }

    override val globalInterceptor: () -> Interceptor<AppiumInteraction, AppiumAssertion, AppiumAction>? =
        { KakaoAppium.appiumInterceptor }

    fun check(
        type: AppiumOperationType,
        description: String? = null,
        webElement: WebElement.() -> Unit
    ) {
        check(produceAppiumAssertion(type, description, webElement))
    }

    fun perform(
        type: AppiumOperationType,
        description: String? = null,
        webElement: WebElement.() -> Unit
    ) {
        perform(produceAppiumAction(type, description, webElement))
    }
}