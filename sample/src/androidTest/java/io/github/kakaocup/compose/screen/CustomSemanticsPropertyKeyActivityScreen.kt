package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.sample.CustomSemanticsScreenTestTags

class CustomSemanticsPropertyKeyActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider? = null) :
    ComposeScreen<CustomSemanticsPropertyKeyActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(CustomSemanticsScreenTestTags.Screen) }
) {
    val view: KNode = child {
        hasTestTag(CustomSemanticsScreenTestTags.view)
    }
}
