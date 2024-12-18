package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.sample.DialogActivity

private typealias Tags = DialogActivity.TestTags

class DialogActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider? = null) :
    ComposeScreen<DialogActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(Tags.Screen) }
    ) {
    val showAlertDialogButton: KNode = child {
        hasTestTag(Tags.ShowAlertDialogButton)
    }
    val alertDialog: KNode = KNode {
        hasTestTag(Tags.AlertDialog)
        hasAnyAncestor(androidx.compose.ui.test.isDialog())
    }

    val dismissAlertDialogButton: KNode = KNode {
        hasTestTag(Tags.AlertDialogDismissButton)
        hasAnyAncestor(androidx.compose.ui.test.isDialog())
    }

    val status: KNode = child {
        hasTestTag(Tags.StatusText)
    }
}
