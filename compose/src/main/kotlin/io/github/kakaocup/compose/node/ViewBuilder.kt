package io.github.kakaocup.compose.node

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule

class ViewBuilder(composeTestRule: AndroidComposeTestRule<*, *>) {

    private var semanticsNodeInteractionCollection: SemanticsNodeInteractionCollection =
        composeTestRule.onRoot().onChildren()

    val nodeInteraction: SemanticsNodeInteraction
        get() = semanticsNodeInteractionCollection[position]

    private var position = 0

    fun hasTestTag(testTag: String) {
        addFilter(androidx.compose.ui.test.hasTestTag(testTag))
    }

    fun hasText(text: String) {
        addFilter(androidx.compose.ui.test.hasText(text))
    }

    private fun addFilter(semanticsMatcher: SemanticsMatcher) {
        semanticsNodeInteractionCollection =
            semanticsNodeInteractionCollection.filter(semanticsMatcher)
    }

    fun hasPosition(position: Int) {
        this.position = position
    }
}