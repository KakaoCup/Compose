package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.sample.SemanticsScreenTestTags

class SemanticsExtensionActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider? = null) :
    ComposeScreen<SemanticsExtensionActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(SemanticsScreenTestTags.SemanticsScreeen) }
    ) {

    val backgroundColorAndShapeBox: KNode = child {
        hasTestTag(SemanticsScreenTestTags.BackgroundColorAndShapeBox)
    }

    val backgroundBrushAndShapeBox: KNode = child {
        hasTestTag(SemanticsScreenTestTags.BackgroundBrushAndShapeBox)
    }
}
