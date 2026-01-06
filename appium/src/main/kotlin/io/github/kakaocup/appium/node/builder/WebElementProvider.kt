package io.github.kakaocup.appium.node.builder

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebElement

class WebElementProvider(
    val webElementMatcher: WebElementMatcher,
    private val driver: AppiumDriver,
) {

    fun provideWebElement(): WebElement {
        return driver.findElement(webElementMatcher.by)
    }
}