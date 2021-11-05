package io.github.kakaocup.compose.node.action

import androidx.compose.ui.test.*
import androidx.compose.ui.text.TextRange
import io.github.kakaocup.compose.intercept.delegate.ComposeDelegate
import io.github.kakaocup.compose.intercept.operation.ComposeOperationType

interface TextActions {
    val delegate: ComposeDelegate

    /**
     * Clears the text in this node in similar way to IME.
     */
    fun performTextClearance() {
        delegate.perform(ComposeTextActionType.PERFORM_TEXT_CLEARANCE) { performTextClearance() }
    }

    /**
     * Sends the given text to this node in similar way to IME.
     *
     * @param text Text to send.
     */
    fun performTextInput(text: String) {
        delegate.perform(ComposeTextActionType.PERFORM_TEXT_INPUT) { performTextInput(text) }
    }

    /**
     * Sends the given selection to this node in similar way to IME.
     *
     * @param selection selection to send
     */
    @ExperimentalTestApi
    fun performTextInputSelection(selection: TextRange) {
        delegate.perform(ComposeTextActionType.PERFORM_TEXT_INPUT_SELECTION) { performTextInputSelection(selection) }
    }

    /**
     * Replaces existing text with the given text in this node in similar way to IME.
     *
     * This does not reflect text selection. All the text gets cleared out and new inserted.
     *
     * @param text Text to send.
     */
    fun performTextReplacement(text: String) {
        delegate.perform(ComposeTextActionType.PERFORM_TEXT_REPLACEMENT) { performTextReplacement(text) }
    }

    /**
     * Sends to this node the IME action associated with it in similar way to IME.
     *
     * The node needs to define its IME action in semantics.
     *
     * @throws AssertionError if the node does not support input or does not define IME action.
     * @throws IllegalStateException if tne node did not establish input connection (e.g. is not
     * focused)
     */
    fun performImeAction() {
        delegate.perform(ComposeTextActionType.PERFORM_IME_ACTION) { performImeAction() }
    }

    enum class ComposeTextActionType : ComposeOperationType {
        PERFORM_TEXT_CLEARANCE,
        PERFORM_TEXT_INPUT,
        PERFORM_TEXT_INPUT_SELECTION,
        PERFORM_TEXT_REPLACEMENT,
        PERFORM_IME_ACTION,
    }
}