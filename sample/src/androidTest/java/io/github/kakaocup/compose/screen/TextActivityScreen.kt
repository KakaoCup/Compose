package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.foundation.KTextNode
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.sample.IconActivity
import io.github.kakaocup.compose.sample.TextActivity

class TextActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider? = null) :
    ComposeScreen<TextActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(IconActivity.IconScreenTestTags.IconScreen) }
    ) {

    val textWithAllFields: KTextNode = child {
        hasTestTag(TextActivity.TextScreenTestTags.textWithAllFields)
    }

    val textWithSemantic: KTextNode = child {
        hasTestTag(TextActivity.TextScreenTestTags.textWithSemantic)
    }

    val textWithoutStyle: KTextNode = child {
        hasTestTag(TextActivity.TextScreenTestTags.textWithoutStyle)
    }

    val textWithStyle: KTextNode = child {
        hasTestTag(TextActivity.TextScreenTestTags.textWithStyle)
    }
}
