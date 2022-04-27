package io.github.kakaocup.compose.screen.navigation

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen

class SecondNavigationScreen(semanticsProvider: SemanticsNodeInteractionsProvider) : ComposeScreen<SecondNavigationScreen>(
    semanticsProvider = semanticsProvider,
    viewBuilderAction = { hasTestTag("SecondNavigationScreen") }
) {
    val sharedWidget: SharedWidgetNode = child {
        hasTestTag("SharedWidget")
    }
}
