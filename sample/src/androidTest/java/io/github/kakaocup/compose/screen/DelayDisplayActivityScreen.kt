package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.sample.DelayDisplayActivityTestTags

class DelayDisplayActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider? = null) :
    ComposeScreen<DelayDisplayActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(DelayDisplayActivityTestTags.Screen) }
) {

    val mySimpleText: KNode = child {
        hasTestTag(DelayDisplayActivityTestTags.mySimpleText)
    }
}
