package io.github.kakaocup.compose.intercept.delegate

import androidx.compose.ui.test.SemanticsNodeInteraction
import io.github.kakaocup.compose.KakaoCompose
import io.github.kakaocup.compose.intercept.base.Interceptor
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion
import io.github.kakaocup.compose.intercept.operation.ComposeOperationBaseImpl
import io.github.kakaocup.compose.intercept.operation.ComposeOperationType

/**
 * Compose implementation of Base delegate interface for Kakao-Compose
 */
class ComposeDelegate(
    private val nodeProvider: () -> SemanticsNodeInteraction,
    private val parentDelegate: ComposeDelegate?,
) : Delegate<ComposeInteraction, ComposeAssertion, ComposeAction> {

    var currentInterceptor: Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>? = null

    override val interaction: ComposeInteraction
        get() {
            val semanticsInteraction = nodeProvider.invoke()
            return ComposeInteraction(semanticsInteraction)
        }

    override val nodeInterceptors: () -> Iterable<Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>> = {
        val currentList = currentInterceptor?.let { listOf(it) } ?: emptyList()
        val parentList = parentDelegate?.nodeInterceptors?.invoke() ?: emptyList()
        currentList + parentList
    }

    override val globalInterceptor: () -> Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>? = { KakaoCompose.composeInterceptor }

    fun check(type: ComposeOperationType,
              description: String? = null,
              action: SemanticsNodeInteraction.() -> Unit) {
        val composeAssertion = ComposeOperationBaseImpl(type, description, action)
        check(composeAssertion)
    }

    fun perform(type: ComposeOperationType,
                description: String? = null,
                action: SemanticsNodeInteraction.() -> Unit) {
        val composeAction = ComposeOperationBaseImpl(type, description, action)
        perform(composeAction)
    }
}