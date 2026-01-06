package io.github.kakaocup.appium

import io.appium.java_client.AppiumDriver
import io.github.kakaocup.appium.node.element.AppiumScreen
import io.github.kakaocup.appium.node.element.KWebElement

class MainActivityScreen(driver: AppiumDriver? = null) : AppiumScreen<MainActivityScreen>(
    driver = driver
) {
    val button1: KWebElement = onWebElement {
        android {
            hasText("Button 1")
        }
    }
}