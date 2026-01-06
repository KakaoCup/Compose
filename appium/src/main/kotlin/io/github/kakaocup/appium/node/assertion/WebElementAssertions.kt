package io.github.kakaocup.appium.node.assertion

import io.github.kakaocup.appium.intercept.delegate.WebElementDelegate
import io.github.kakaocup.appium.intercept.operation.AppiumOperationType
import kotlin.test.assertEquals

interface WebElementAssertions {
    val delegate: WebElementDelegate

    fun assertTextEquals(text: String) {
        delegate.check(AppiumBaseAssertionType.ASSERT_TEXT_EQUALS) {
            assertEquals(text, this.text)
        }
    }

    enum class AppiumBaseAssertionType : AppiumOperationType {
        ASSERT_TEXT_EQUALS,
    }
}