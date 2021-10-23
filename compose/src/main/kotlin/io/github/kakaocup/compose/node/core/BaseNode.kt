package io.github.kakaocup.compose.node.core

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.hasParent
import io.github.kakaocup.compose.intercept.delegate.ComposeDelegate
import io.github.kakaocup.compose.intercept.delegate.ComposeInterceptable
import io.github.kakaocup.compose.node.action.NodeActions
import io.github.kakaocup.compose.node.action.TextActions
import io.github.kakaocup.compose.node.assertion.NodeAssertions
import io.github.kakaocup.compose.node.builder.UserMatcher
import io.github.kakaocup.compose.node.builder.ViewBuilder

@ComposeMarker
abstract class BaseNode<out T : BaseNode<T>> internal constructor(
    protected val semanticsProvider: SemanticsNodeInteractionsProvider,
    private val userMatcher: UserMatcher,
    parentNode: BaseNode<T>? = null,
) : KDSL<T>, NodeAssertions, NodeActions, TextActions, ComposeInterceptable {

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        viewBuilderAction: ViewBuilder.() -> Unit,
    ) : this(
        semanticsProvider = semanticsProvider,
        userMatcher = ViewBuilder().apply(viewBuilderAction).build(),
        parentNode = null
    )

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        userMatcher: UserMatcher,
    ) : this(
        semanticsProvider = semanticsProvider,
        userMatcher = userMatcher,
        parentNode = null
    )

    override val delegate: ComposeDelegate = ComposeDelegate(
        nodeProvider = {
            val finalMatcher = if (parentNode == null) userMatcher.matcher else hasParent(parentNode.userMatcher.matcher) and userMatcher.matcher
            semanticsProvider.onAllNodes(finalMatcher)[userMatcher.position]
        },
        parentDelegate = parentNode?.delegate
    )

    protected inline fun <reified N> child(function: ViewBuilder.() -> Unit): N {
        return N::class.java.getConstructor(
            SemanticsNodeInteractionsProvider::class.java,
            UserMatcher::class.java,
            BaseNode::class.java,
        ).newInstance(
            semanticsProvider,
            ViewBuilder().apply(function).build(),
            this,
        )
    }
}