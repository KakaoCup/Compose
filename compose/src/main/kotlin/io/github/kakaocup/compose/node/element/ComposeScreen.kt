package io.github.kakaocup.compose.node.element

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.core.ComposeMarker
import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.node.builder.UserMatcher
import io.github.kakaocup.compose.node.builder.ViewBuilder

@Suppress("UNCHECKED_CAST")
@ComposeMarker
open class ComposeScreen<out T : ComposeScreen<T>> : BaseNode<T> {

    internal constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        userMatcher: UserMatcher,
        parentNode: ComposeScreen<T>? = null,
    ) : super(semanticsProvider, userMatcher, parentNode)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        viewBuilderAction: ViewBuilder.() -> Unit,
    ) : super(semanticsProvider, viewBuilderAction)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        userMatcher: UserMatcher = UserMatcher(
            matcher = SemanticsMatcher(
                description = "Empty matcher",
                matcher = { true}
            )
        ),
    ) : super(semanticsProvider, userMatcher)

    fun onNode(viewBuilderAction: ViewBuilder.() -> Unit) = KNode(semanticsProvider, viewBuilderAction)

    companion object {
        inline fun <reified T : ComposeScreen<T>> onComposeScreen(
            semanticsProvider: SemanticsNodeInteractionsProvider,
            noinline function: T.() -> Unit
        ): T {
            return T::class.java
                .getDeclaredConstructor(
                    SemanticsNodeInteractionsProvider::class.java
                )
                .newInstance(semanticsProvider)
                .apply { this(function) }
        }
    }
}