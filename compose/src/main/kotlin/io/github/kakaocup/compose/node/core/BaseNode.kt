package io.github.kakaocup.compose.node.core

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.hasAnyAncestor
import io.github.kakaocup.compose.exception.KakaoComposeException
import io.github.kakaocup.compose.intercept.delegate.ComposeDelegate
import io.github.kakaocup.compose.intercept.delegate.ComposeInterceptable
import io.github.kakaocup.compose.node.action.NodeActions
import io.github.kakaocup.compose.node.action.TextActions
import io.github.kakaocup.compose.node.assertion.NodeAssertions
import io.github.kakaocup.compose.node.assertion.TextResourcesNodeAssertions
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.builder.NodeProvider
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.utilities.checkNotNull
import io.github.kakaocup.compose.utilities.orGlobal

@ComposeMarker
abstract class BaseNode<out T : BaseNode<T>> constructor(
    @PublishedApi internal var semanticsProvider: SemanticsNodeInteractionsProvider? = null,
    private var nodeMatcher: NodeMatcher? = null,
    private var parentNode: BaseNode<*>? = null,
) : KDSL<T>,
    NodeAssertions,
    TextResourcesNodeAssertions,
    NodeActions,
    TextActions,
    ComposeInterceptable {

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
        viewBuilderAction: ViewBuilder.() -> Unit,
    ) : this(
        semanticsProvider = semanticsProvider,
        nodeMatcher = ViewBuilder().apply(viewBuilderAction).build(),
        parentNode = null
    )

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
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
                    position = requireNodeMatcher().position,
                    useUnmergedTree = requireNodeMatcher().useUnmergedTree
                ),
                semanticsProvider = requireSemanticsProvider()
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
            requireSemanticsProvider(),
            ViewBuilder().apply(function).build(),
            this,
        )
    }

    /**
     * Allowed getter for [nodeMatcher].
     * Any [NodeMatcher] must be initialized before use.
     */
    fun requireNodeMatcher(): NodeMatcher {
        return this.nodeMatcher
            ?: throw KakaoComposeException("NodeMatcher is null: Provide via constructor or use `initSemantics` method`")
    }

    /**
     * Allowed getter for [requireSemanticsProvider].
     * Any [SemanticsNodeInteractionsProvider] must be initialized before use.
     */
    fun requireSemanticsProvider(): SemanticsNodeInteractionsProvider {
        return this.semanticsProvider.orGlobal().checkNotNull()
    }

    /**
     * Method for deferred initialization of [BaseNode] constructor parameters.
     * Simplifies the description of child nodes in list nodes.
     */
    fun initSemantics(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        nodeMatcher: NodeMatcher,
        parentNode: BaseNode<*>? = null,
    ) {
        this.semanticsProvider = semanticsProvider
        this.nodeMatcher = nodeMatcher
        this.parentNode = parentNode
    }

    /***
     * Combines semantic matchers from all ancestor nodes
     */
    private fun combineSemanticMatchers(): SemanticsMatcher {
        val semanticsMatcherList = mutableListOf<SemanticsMatcher>()
        var parent = this.parentNode

        while (parent != null) {
            semanticsMatcherList.add(hasAnyAncestor(parent.requireNodeMatcher().matcher))
            parent = parent.parentNode
        }
        semanticsMatcherList.add(this.requireNodeMatcher().matcher)

        return semanticsMatcherList.reduce { finalMatcher, matcher -> finalMatcher and matcher }
    }
}
