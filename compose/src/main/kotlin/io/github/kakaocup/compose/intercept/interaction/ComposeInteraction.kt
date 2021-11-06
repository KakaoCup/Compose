package io.github.kakaocup.compose.intercept.interaction

import androidx.compose.ui.test.SemanticsNodeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion
import io.github.kakaocup.compose.node.builder.NodeProvider

class ComposeInteraction(
    private val nodeProvider: NodeProvider,
) : Interaction<ComposeAssertion, ComposeAction> {

    var semanticsNodeInteraction: SemanticsNodeInteraction = nodeProvider.provideSemanticsNodeInteraction()
        private set

    override fun check(assertion: ComposeAssertion) {
        assertion.execute(semanticsNodeInteraction)
    }

    override fun perform(action: ComposeAction) {
        action.execute(semanticsNodeInteraction)
    }

    fun reFindNode() {
        semanticsNodeInteraction = nodeProvider.provideSemanticsNodeInteraction()
    }

    override fun toString(): String {
        val nodeMatcher = nodeProvider.nodeMatcher
        return "matcher: ${nodeMatcher.matcher.description}; " +
                "position: ${nodeMatcher.position}; " +
                "useUnmergedTree: ${nodeMatcher.useUnmergedTree}"
    }
}