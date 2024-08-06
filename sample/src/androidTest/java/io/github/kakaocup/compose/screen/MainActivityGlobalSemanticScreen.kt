package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class MainActivityGlobalSemanticScreen(semanticsProvider: SemanticsNodeInteractionsProvider? = null) :
    ComposeScreen<MainActivityGlobalSemanticScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag("MainScreen") },
    ) {

    val myText1: KNode = child {
        hasTestTag("mySimpleText")
        hasPosition(0)
    }
}
