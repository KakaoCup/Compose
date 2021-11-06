package io.github.kakaocup.compose.intercept.interaction

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.printToString
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

class ComposeInteraction(
    private val nodeProvider: () -> SemanticsNodeInteraction
) : Interaction<ComposeAssertion, ComposeAction> {

    private var semanticsNodeInteraction: SemanticsNodeInteraction = nodeProvider.invoke()

    override fun check(assertion: ComposeAssertion) {
        assertion.execute(semanticsNodeInteraction)
    }

    override fun perform(action: ComposeAction) {
        action.execute(semanticsNodeInteraction)
    }

    fun reFindNode() {
        semanticsNodeInteraction = nodeProvider.invoke()
    }

    override fun toString(): String {
        return semanticsNodeInteraction.printToString(maxDepth = 1)
    }
}