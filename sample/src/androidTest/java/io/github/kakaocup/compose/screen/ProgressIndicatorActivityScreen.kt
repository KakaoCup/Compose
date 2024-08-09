package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.foundation.KProgressIndicatorNode
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.sample.ProgressIndicatorActivity

class ProgressIndicatorActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider? = null) :
    ComposeScreen<ProgressIndicatorActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(ProgressIndicatorActivity.TextScreenTestTags.ProgressIndicatorScreen) }
    ) {
    val circularWithProgress: KProgressIndicatorNode = child {
        hasTestTag(ProgressIndicatorActivity.TextScreenTestTags.CircularWithProgress)
    }

    val circularWithoutProgress: KProgressIndicatorNode = child {
        hasTestTag(ProgressIndicatorActivity.TextScreenTestTags.CircularWithoutProgress)
    }

    val linearWithProgress: KProgressIndicatorNode = child {
        hasTestTag(ProgressIndicatorActivity.TextScreenTestTags.LinearWithProgress)
    }
    val linearWithoutProgress: KProgressIndicatorNode = child {
        hasTestTag(ProgressIndicatorActivity.TextScreenTestTags.LinearWithoutProgress)
    }
}
