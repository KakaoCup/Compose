package io.github.kakaocup.appium.node.action

import io.github.kakaocup.appium.intercept.delegate.WebElementDelegate
import io.github.kakaocup.appium.intercept.operation.AppiumOperationType

interface WebElementActions {
    val delegate: WebElementDelegate

    /**
     * Performs a click action on the element represented by the given web element.
     */
    fun performClick() {
        delegate.perform(AppiumBaseActionType.PERFORM_CLICK) { this.click() }
    }

    enum class AppiumBaseActionType : AppiumOperationType {
        PERFORM_CLICK,
    }
}
