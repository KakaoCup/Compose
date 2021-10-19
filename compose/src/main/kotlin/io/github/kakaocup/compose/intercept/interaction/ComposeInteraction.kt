package io.github.kakaocup.compose.intercept.interaction

import androidx.compose.ui.test.SemanticsNodeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

class ComposeInteraction(
    private val semanticsNodeInteraction: SemanticsNodeInteraction
) : Interaction<ComposeAssertion, ComposeAction> {

    override fun check(assertion: ComposeAssertion) {
        assertion.invoke(semanticsNodeInteraction)
    }

    override fun perform(action: ComposeAction) {
        action.invoke(semanticsNodeInteraction)
    }
}