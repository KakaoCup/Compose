package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.KNode

class MainActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<MainActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag("MainScreen") }
    ) {

    val myText1: KNode = child {
        hasTestTag("mySimpleText")
        hasPosition(0)
    }

    val myText2: KNode = child {
        hasTestTag("mySimpleText")
        hasPosition(1)
    }

    val myButton: KNode = child {
        hasTestTag("myTestButton")
        hasText("Button 1")
    }
}