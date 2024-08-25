package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.foundation.KButtonNode
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.sample.ButtonActivity

class ButtonActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider? = null) :
    ComposeScreen<ButtonActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(ButtonActivity.ButtonScreenTestTags.ButtonScreen) }
    ) {
    val button: KButtonNode = child {
        hasTestTag(ButtonActivity.ButtonScreenTestTags.Button)
    }

    val outlinedButton: KButtonNode = child {
        hasTestTag(ButtonActivity.ButtonScreenTestTags.OutlinedButton)
    }

    val textButton: KButtonNode = child {
        hasTestTag(ButtonActivity.ButtonScreenTestTags.TextButton)
    }
}
