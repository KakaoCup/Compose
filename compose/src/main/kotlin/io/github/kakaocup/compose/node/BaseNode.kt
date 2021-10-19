package io.github.kakaocup.compose.node

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.hasParent
import io.github.kakaocup.compose.ComposeMarker
import io.github.kakaocup.compose.node.core.KDSL

@ComposeMarker
abstract class BaseNode<out T : BaseNode<T>> internal constructor(
    protected val semanticsProvider: SemanticsNodeInteractionsProvider,
    protected val userMatcher: UserMatcher,
    parentMatcher: SemanticsMatcher? = null,
) : KDSL<T>, NodeAssertions, NodeActions, TextActions {

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        viewBuilderAction: ViewBuilder.() -> Unit,
    ) : this(
        semanticsProvider = semanticsProvider,
        userMatcher = ViewBuilder().apply(viewBuilderAction).build(),
        parentMatcher = null
    )

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        userMatcher: UserMatcher,
    ) : this(
        semanticsProvider = semanticsProvider,
        userMatcher = userMatcher,
        parentMatcher = null
    )

    val composeInteractionDelegate: ComposeInteractionDelegate = ComposeInteractionDelegate(
        nodeProvider = {
            val finalMatcher = if (parentMatcher == null) userMatcher.matcher else hasParent(parentMatcher) and userMatcher.matcher
            semanticsProvider.onAllNodes(finalMatcher)[userMatcher.position]
        }
    )

    override val nodeInteraction: SemanticsNodeInteraction = composeInteractionDelegate.nodeInteraction

    protected inline fun <reified N> BaseNode<*>.child(function: ViewBuilder.() -> Unit): N {
        return N::class.java.getConstructor(
            SemanticsNodeInteractionsProvider::class.java,
            UserMatcher::class.java,
            SemanticsMatcher::class.java,
        ).newInstance(
            semanticsProvider,
            ViewBuilder().apply(function).build(),
            userMatcher.matcher,
        )
    }
}