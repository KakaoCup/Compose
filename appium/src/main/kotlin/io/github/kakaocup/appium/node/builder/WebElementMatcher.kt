package io.github.kakaocup.appium.node.builder

import org.openqa.selenium.By

data class WebElementMatcher(
    val by: By,
    val position: Int = 0,
)