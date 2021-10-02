package io.github.kakaocup.compose.node

import androidx.compose.ui.test.*
import androidx.compose.ui.text.TextRange

interface TextActions {
    val nodeInteraction: SemanticsNodeInteraction

    /**
     * Clears the text in this node in similar way to IME.
     */
    fun performTextClearance() {
        nodeInteraction.performTextClearance()
    }

    /**
     * Sends the given text to this node in similar way to IME.
     *
     * @param text Text to send.
     */
    fun performTextInput(text: String) {
        nodeInteraction.performTextInput(text)
    }

    /**
     * Sends the given selection to this node in similar way to IME.
     *
     * @param selection selection to send
     */
    @ExperimentalTestApi
    fun performTextInputSelection(selection: TextRange) {
        nodeInteraction.performTextInputSelection(selection)
    }

    /**
     * Replaces existing text with the given text in this node in similar way to IME.
     *
     * This does not reflect text selection. All the text gets cleared out and new inserted.
     *
     * @param text Text to send.
     */
    fun performTextReplacement(text: String) {
        nodeInteraction.performTextReplacement(text)
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
        nodeInteraction.performImeAction()
    }
}