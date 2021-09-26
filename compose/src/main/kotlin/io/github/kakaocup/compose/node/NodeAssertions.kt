package io.github.kakaocup.compose.node

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains

interface NodeAssertions {
    val nodeInteraction: SemanticsNodeInteraction

    fun isDisplayed() {
        nodeInteraction.assertIsDisplayed()
    }

    fun textContains(
        value: String,
        substring: Boolean = false,
        ignoreCase: Boolean = false
    ) {
        nodeInteraction.assertTextContains(value, substring, ignoreCase)
    }

    fun doesNotExist() {
        nodeInteraction.assertDoesNotExist()
    }
}