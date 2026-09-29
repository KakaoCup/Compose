package io.github.kakaocup.compose.node.core

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.junit4.ComposeTestRule
import io.github.kakaocup.compose.node.builder.NodeMatcher

@PublishedApi
internal actual inline fun <reified N> createChildNode(
    semanticsProvider: SemanticsNodeInteractionsProvider,
    nodeMatcher: NodeMatcher,
    parentNode: BaseNode<*>,
): N = N::class.java
    .getConstructor(
        SemanticsNodeInteractionsProvider::class.java,
        NodeMatcher::class.java,
        BaseNode::class.java,
    )
    .newInstance(semanticsProvider, nodeMatcher, parentNode)

/**
 * Android-only `waitUntil` helper. Depends on JUnit4-flavoured [ComposeTestRule],
 * which only exists on Android. iOS tests should use `ComposeUiTest.waitUntil` directly.
 */
fun BaseNode<*>.waitUntil(
    composeTestRule: ComposeTestRule = semanticsProvider as ComposeTestRule,
    timeoutMillis: Long = 1_000,
    condition: SemanticsNodeInteraction.() -> Unit
) {
    composeTestRule.waitUntil(timeoutMillis) {
        try {
            condition.invoke(this.delegate.interaction.semanticsNodeInteraction)
            true
        } catch (e: AssertionError) {
            false
        }
    }
}
