package io.github.kakaocup.appium.intercept.operation

import org.openqa.selenium.WebElement

interface AppiumAction : AppiumOperation<WebElement>

interface AppiumAssertion : AppiumOperation<WebElement>

fun produceAppiumAction(
    type: AppiumOperationType,
    description: String?,
    webElement: WebElement.() -> Unit
): AppiumAction = object : AppiumAction {
    override val type = type
    override val description: String? = description
    override fun execute(innerWebElement: WebElement) = webElement.invoke(innerWebElement)
    override fun toString(): String {
        return "AppiumAction (description=$description)"
    }
}

fun produceAppiumAssertion(
    type: AppiumOperationType,
    description: String?,
    webElement: WebElement.() -> Unit
): AppiumAssertion = object : AppiumAssertion {
    override val type = type
    override val description: String? = description
    override fun execute(innerWebElement: WebElement) = webElement.invoke(innerWebElement)
    override fun toString(): String {
        return "AppiumAssertion (escription=$description)"
    }
}