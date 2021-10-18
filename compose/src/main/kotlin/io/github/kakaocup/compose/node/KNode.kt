package io.github.kakaocup.compose.node

import androidx.compose.ui.test.SemanticsNodeInteraction
import io.github.kakaocup.compose.node.core.KDSL
import io.github.kakaocup.compose.screen.ComposeScreen

open class KNode : KDSL<KNode>, NodeAssertions, NodeActions, TextActions {
    override val nodeInteraction: SemanticsNodeInteraction

    constructor(composeScreen: ComposeScreen<*>, useUnmergedTree : Boolean = false, function: ViewBuilder.() -> Unit) {
        nodeInteraction = ViewBuilder(composeScreen.composeTestRule, useUnmergedTree).apply(function).nodeInteraction
    }
}