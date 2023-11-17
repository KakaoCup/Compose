package io.github.kakaocup.compose.node.core

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.hasAnyAncestor
import io.github.kakaocup.compose.intercept.delegate.ComposeDelegate
import io.github.kakaocup.compose.intercept.delegate.ComposeInterceptable
import io.github.kakaocup.compose.node.action.NodeActions
import io.github.kakaocup.compose.node.action.TextActions
import io.github.kakaocup.compose.node.assertion.NodeAssertions
import io.github.kakaocup.compose.node.assertion.TextResourcesNodeAssertions
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.builder.NodeProvider
import io.github.kakaocup.compose.node.builder.ViewBuilder

@ComposeMarker
abstract class BaseNode<out T : BaseNode<T>> constructor(
    @PublishedApi internal val semanticsProvider: SemanticsNodeInteractionsProvider,
    private val nodeMatcher: NodeMatcher,
    private val parentNode: BaseNode<*>? = null,
) : KDSL<T>,
    NodeAssertions,
    TextResourcesNodeAssertions,
    NodeActions,
    TextActions,
    ComposeInterceptable {

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        viewBuilderAction: ViewBuilder.() -> Unit,
    ) : this(
        semanticsProvider = semanticsProvider,
        nodeMatcher = ViewBuilder().apply(viewBuilderAction).build(),
        parentNode = null
    )

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        nodeMatcher: NodeMatcher,
    ) : this(
        semanticsProvider = semanticsProvider,
        nodeMatcher = nodeMatcher,
        parentNode = null
    )

    override val delegate: ComposeDelegate by lazy(LazyThreadSafetyMode.NONE) {
        ComposeDelegate(
            nodeProvider = NodeProvider(
                nodeMatcher = NodeMatcher(
                    matcher = combineSemanticMatchers(),
                    position = nodeMatcher.position,
                    useUnmergedTree = nodeMatcher.useUnmergedTree
                ),
                semanticsProvider = semanticsProvider
            ),
            parentDelegate = parentNode?.delegate
        )
    }

    inline fun <reified N> child(function: ViewBuilder.() -> Unit): N {
        return N::class.java.getConstructor(
            SemanticsNodeInteractionsProvider::class.java,
            NodeMatcher::class.java,
            BaseNode::class.java,
        ).newInstance(
            semanticsProvider,
            ViewBuilder().apply(function).build(),
            this,
        )
    }

    /***
     * Combines semantic matchers from all ancestor nodes
     */
    private fun combineSemanticMatchers(): SemanticsMatcher {
        val semanticsMatcherList = mutableListOf<SemanticsMatcher>()
        var parent = this.parentNode

        while (parent != null) {
            semanticsMatcherList.add(hasAnyAncestor(parent.nodeMatcher.matcher))
            parent = parent.parentNode
        }
        semanticsMatcherList.add(this.nodeMatcher.matcher)

        return semanticsMatcherList.reduce { finalMatcher, matcher -> finalMatcher and matcher }
    }
}
