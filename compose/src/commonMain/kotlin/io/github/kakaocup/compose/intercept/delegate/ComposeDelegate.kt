package io.github.kakaocup.compose.intercept.delegate

import androidx.compose.ui.test.SemanticsNodeInteraction
import io.github.kakaocup.compose.KakaoCompose
import io.github.kakaocup.compose.intercept.base.Interceptor
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.*
import io.github.kakaocup.compose.node.builder.NodeProvider

/**
 * Compose implementation of Base delegate interface for Kakao-Compose
 */
class ComposeDelegate(
    nodeProvider: NodeProvider,
    private val parentDelegate: ComposeDelegate?,
) : Delegate<ComposeInteraction, ComposeAssertion, ComposeAction> {

    var currentInterceptor: Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>? = null

    override val interaction = ComposeInteraction(nodeProvider)

    override val nodeInterceptors: () -> Iterable<Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>> = {
        val currentList = currentInterceptor?.let { listOf(it) } ?: emptyList()
        val parentList = parentDelegate?.nodeInterceptors?.invoke() ?: emptyList()
        currentList + parentList
    }

    override val globalInterceptor: () -> Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>? = { KakaoCompose.composeInterceptor }

    fun check(type: ComposeOperationType,
              description: String? = null,
              action: SemanticsNodeInteraction.() -> Unit) {
        val composeAssertion = produceComposeAssertion(type, description, action)
        check(composeAssertion)
    }

    fun perform(type: ComposeOperationType,
                description: String? = null,
                action: SemanticsNodeInteraction.() -> Unit) {
        val composeAction = produceComposeAction(type, description, action)
        perform(composeAction)
    }
}