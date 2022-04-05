package io.github.kakaocup.compose.screen.navigation

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class FirstNavigationScreen(semanticsProvider: SemanticsNodeInteractionsProvider) : ComposeScreen<FirstNavigationScreen>(
    semanticsProvider = semanticsProvider,
    viewBuilderAction = { hasTestTag("FirstNavigationScreen") }
) {
    val sharedWidget: SharedWidgetNode = child {
        hasTestTag("SharedWidget")
    }

    val navigationButton: KNode = child {
        hasTestTag("NavigateSecondScreenButton")
    }
}