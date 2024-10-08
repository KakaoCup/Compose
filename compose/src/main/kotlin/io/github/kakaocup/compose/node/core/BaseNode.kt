package io.github.kakaocup.compose.node.core

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
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
    var semanticsProvider: SemanticsNodeInteractionsProvider? = null,
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
                    position = nodeMatcher.checkNotNull().position,
                    useUnmergedTree = nodeMatcher.checkNotNull().useUnmergedTree
                ),
                semanticsProvider = semanticsProvider.orGlobal().checkNotNull()
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
            semanticsProvider.orGlobal().checkNotNull(),
            ViewBuilder().apply(function).build(),
            this,
        )
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
            semanticsMatcherList.add(hasAnyAncestor(parent.nodeMatcher.checkNotNull().matcher))
            parent = parent.parentNode
        }
        semanticsMatcherList.add(this.nodeMatcher.checkNotNull().matcher)

        return semanticsMatcherList.reduce { finalMatcher, matcher -> finalMatcher and matcher }
    }

    fun waitUntil(
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
}
