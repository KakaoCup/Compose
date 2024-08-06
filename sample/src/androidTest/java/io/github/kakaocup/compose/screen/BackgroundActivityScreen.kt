package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.sample.BackgroundSemanticsScreenTestTags

class BackgroundActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider? = null) :
    ComposeScreen<BackgroundActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(BackgroundSemanticsScreenTestTags.BackgroundScreen) }
    ) {

    val backgroundColorAndShapeBox: KNode = child {
        hasTestTag(BackgroundSemanticsScreenTestTags.BackgroundColorAndShapeBox)
    }

    val backgroundBrushAndShapeBox: KNode = child {
        hasTestTag(BackgroundSemanticsScreenTestTags.BackgroundBrushAndShapeBox)
    }
}
