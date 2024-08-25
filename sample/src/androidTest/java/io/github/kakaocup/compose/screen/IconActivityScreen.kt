package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.foundation.KIconNode
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.sample.BackgroundSemanticsScreenTestTags
import io.github.kakaocup.compose.sample.IconActivity

class IconActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider? = null) :
    ComposeScreen<IconActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(IconActivity.IconScreenTestTags.IconScreen) }
    ) {

    val iconDrawableRes: KIconNode = child {
        hasTestTag(IconActivity.IconScreenTestTags.iconDrawableRes)
    }

    val iconImageVector: KIconNode = child {
        hasTestTag(IconActivity.IconScreenTestTags.iconImageVector)
    }
}
