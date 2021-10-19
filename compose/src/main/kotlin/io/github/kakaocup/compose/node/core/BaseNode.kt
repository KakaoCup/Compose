package io.github.kakaocup.compose.node.core

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.hasParent
import io.github.kakaocup.compose.intercept.base.Interceptor
import io.github.kakaocup.compose.intercept.delegate.ComposeDelegate
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion
import io.github.kakaocup.compose.node.action.NodeActions
import io.github.kakaocup.compose.node.action.TextActions
import io.github.kakaocup.compose.node.assertion.NodeAssertions
import io.github.kakaocup.compose.node.builder.UserMatcher
import io.github.kakaocup.compose.node.builder.ViewBuilder
import java.util.*

@ComposeMarker
abstract class BaseNode<out T : BaseNode<T>> internal constructor(
    protected val semanticsProvider: SemanticsNodeInteractionsProvider,
    private val userMatcher: UserMatcher,
    parentNode: BaseNode<T>? = null,
) : KDSL<T>, NodeAssertions, NodeActions, TextActions {

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

    override val node: ComposeDelegate = ComposeDelegate(
        nodeProvider = {
            val finalMatcher = if (parentNode == null) userMatcher.matcher else hasParent(parentNode.userMatcher.matcher) and userMatcher.matcher
            semanticsProvider.onAllNodes(finalMatcher)[userMatcher.position]
        }
    )

    private var composeInterceptor: Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>? = null
    private var isActive = false

    /**
     * Sets the interceptors for the Node.
     * Interceptors will be invoked on all interactions while the Node is active.
     *
     * The node is considered `active` when it is invoked in one of the following ways:
     * ```
     * val node = SomeNode()
     *
     * node { // Active
     *     childNode { click() }
     *     ...
     * } // Inactive
     * ```
     *
     * If you use nesting nodes, all interceptors of the nodes that became active will be invoked
     * in LIFO order (using Deque).
     *
     * @param builder Builder of interceptor
     *
     * @see Interceptor
     */
    fun intercept(builder: Interceptor.Builder<ComposeInteraction, ComposeAssertion, ComposeAction>.() -> Unit) {
        if (isActive) {
            removeInterceptors()
        }

        val interceptor = Interceptor.Builder<ComposeInteraction, ComposeAssertion, ComposeAction>().apply(builder).build()
        this.composeInterceptor = interceptor

        if (isActive) {
            addInterceptors()
        }
    }

    /**
     * Removes the interceptors from the node.
     *
     * @see intercept
     * @see Interceptor
     */
    fun reset() {
        if (isActive) {
            removeInterceptors()
        }

        composeInterceptor = null
    }

    /**
     * Operator that allows usage of DSL style
     *
     * @param function Tail lambda with receiver which is your node
     */
    override operator fun invoke(function: T.() -> Unit) {
        isActive = true
        addInterceptors()

        super.invoke(function)

        isActive = false
        removeInterceptors()
    }

    private fun addInterceptors() {
        composeInterceptor?.let { composeInterceptors.offerFirst(it) }
    }

    private fun removeInterceptors() {
        composeInterceptor?.let { composeInterceptors.removeFirstOccurrence(it) }
    }

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

    companion object {
        internal val composeInterceptors: Deque<Interceptor<ComposeInteraction, ComposeAssertion, ComposeAction>> = LinkedList()
    }
}