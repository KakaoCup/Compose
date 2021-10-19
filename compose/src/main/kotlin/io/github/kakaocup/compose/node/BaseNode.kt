package io.github.kakaocup.compose.node

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.hasParent
import io.github.kakaocup.compose.ComposeMarker
import io.github.kakaocup.compose.intercept.base.Interceptor
import io.github.kakaocup.compose.intercept.delegate.ComposeDelegate
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion
import io.github.kakaocup.compose.node.core.KDSL
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
     * Sets the interceptors for the screen.
     * Interceptors will be invoked on all interactions while the screen is active.
     *
     * The screen is considered `active` when it is invoked in one of the following ways:
     * ```
     * val screen = SomeScreen()
     *
     * screen { // Active
     *     view { click() }
     *     ...
     * } // Inactive
     *
     * // OR
     *
     * onScreen<SomeScreen>() { // Active
     *     view { click() }
     *     ...
     * } // Inactive
     * ```
     *
     * If you use nesting screens, all interceptors of the screens that became active will be invoked
     * in LIFO order (using Deque).
     *
     * @param configurator Configuration of the interceptors
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
     * Removes the interceptors from the screen.
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
     * @param function Tail lambda with receiver which is your screen
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