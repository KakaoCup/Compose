package io.github.kakaocup.appium.intercept.interaction

import io.github.kakaocup.appium.intercept.operation.AppiumAction
import io.github.kakaocup.appium.intercept.operation.AppiumAssertion
import io.github.kakaocup.appium.node.builder.WebElementProvider
import org.openqa.selenium.WebElement

class AppiumInteraction(
    val appiumWebElementProvider: WebElementProvider,
) : Interaction<AppiumAssertion, AppiumAction> {

    var webElement: WebElement = appiumWebElementProvider.provideWebElement()
        private set

    override fun check(assertion: AppiumAssertion) {
        assertion.execute(webElement)
    }

    override fun perform(action: AppiumAction) {
        action.execute(webElement)
    }

    fun reFindNode() {
        webElement = appiumWebElementProvider.provideWebElement()
    }

    override fun toString(): String {
        val nodeMatcher = appiumWebElementProvider.webElementMatcher
        return "by: ${nodeMatcher.by}; " +
                "position: ${nodeMatcher.position};"
    }
}