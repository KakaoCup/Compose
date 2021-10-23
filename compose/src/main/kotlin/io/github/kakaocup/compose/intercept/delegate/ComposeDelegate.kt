package io.github.kakaocup.compose.intercept.delegate

import androidx.compose.ui.test.SemanticsNodeInteraction
import io.github.kakaocup.compose.KakaoCompose
import io.github.kakaocup.compose.intercept.base.Interceptor
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

/**
 * Compose implementation of Base delegate interface for Kakao-Compose
 */
class ComposeDelegate(
    private val nodeProvider: () -> SemanticsNodeInteraction,
    private val parentDelegate: ComposeDelegate?,
) : Delegate<ComposeInteraction, ComposeAssertion, ComposeAction> {

    var currentInterceptor: Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>? = null

    override val interaction: ComposeInteraction by lazy(LazyThreadSafetyMode.NONE) {
        val semanticsInteraction = nodeProvider.invoke()
        ComposeInteraction(semanticsInteraction)
    }

    override val nodeInterceptors: () -> Iterable<Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>> = {
        val currentList = currentInterceptor?.let { listOf(it) } ?: emptyList()
        val parentList = parentDelegate?.nodeInterceptors?.invoke() ?: emptyList()
        currentList + parentList
    }

    override val globalInterceptor: () -> Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>? = { KakaoCompose.composeInterceptor }
}