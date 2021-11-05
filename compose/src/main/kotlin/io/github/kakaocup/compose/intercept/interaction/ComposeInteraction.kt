package io.github.kakaocup.compose.intercept.interaction

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.printToString
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

class ComposeInteraction(
    private val semanticsNodeInteraction: SemanticsNodeInteraction
) : Interaction<ComposeAssertion, ComposeAction> {

    override fun check(assertion: ComposeAssertion) {
        assertion.execute(semanticsNodeInteraction)
    }

    override fun perform(action: ComposeAction) {
        action.execute(semanticsNodeInteraction)
    }

    override fun toString(): String {
        return semanticsNodeInteraction.printToString(maxDepth = 1)
    }
}