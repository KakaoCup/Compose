package io.github.kakaocup.compose.intercept.delegate

import androidx.compose.ui.test.SemanticsNodeInteraction
import io.github.kakaocup.compose.KakaoCompose
import io.github.kakaocup.compose.intercept.base.Interceptor
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion
import io.github.kakaocup.compose.node.core.BaseNode

class ComposeDelegate(
    private val nodeProvider: () -> SemanticsNodeInteraction
) : Delegate<ComposeInteraction, ComposeAssertion, ComposeAction> {

    override val interaction: ComposeInteraction by lazy(LazyThreadSafetyMode.NONE) {
        val semanticsInteraction = nodeProvider.invoke()
        ComposeInteraction(semanticsInteraction)
    }

    fun perform(action: (SemanticsNodeInteraction) -> Unit) {
        if (!interceptPerform(action)) interaction.perform(action)
    }

    fun check(assertion: (SemanticsNodeInteraction) -> Unit) {
        if (!interceptCheck(assertion)) interaction.check(assertion)
    }

    override fun nodeInterceptors(): Iterable<Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>> = BaseNode.composeInterceptors

    override fun globalInterceptor(): Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>? = KakaoCompose.composeInterceptor
}