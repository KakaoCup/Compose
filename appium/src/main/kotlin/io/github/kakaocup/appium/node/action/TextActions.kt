package io.github.kakaocup.appium.node.action

import io.github.kakaocup.appium.intercept.delegate.WebElementDelegate
import io.github.kakaocup.appium.intercept.operation.AppiumOperationType

interface TextActions {
    val delegate: WebElementDelegate

    /**
     * Sends the given text to this node in similar way to IME.
     *
     * @param text Text to send.
     */
    fun performTextInput(text: String) {
        delegate.perform(AppiumTextActionType.PERFORM_TEXT_INPUT) { performTextInput(text) }
    }

    enum class AppiumTextActionType : AppiumOperationType {
        PERFORM_TEXT_INPUT,
    }
}