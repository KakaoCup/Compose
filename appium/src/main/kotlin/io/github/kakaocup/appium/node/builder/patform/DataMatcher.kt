package io.github.kakaocup.appium.node.builder.patform

import org.openqa.selenium.By

open class DataMatcher {
    val matchers = mutableListOf<By>()

    fun addFilter(matcher: By) {
        matchers.add(matcher)
    }

    fun hasText(text: String) = addFilter(By.xpath("//*[text()='$text']"))
}