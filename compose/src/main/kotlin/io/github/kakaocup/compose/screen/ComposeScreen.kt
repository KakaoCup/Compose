package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.ComposeMarker
import io.github.kakaocup.compose.node.BaseNode
import io.github.kakaocup.compose.node.KNode
import io.github.kakaocup.compose.node.UserMatcher
import io.github.kakaocup.compose.node.ViewBuilder

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
        userMatcher: UserMatcher,
    ) : super(semanticsProvider, userMatcher)

    fun onNode(viewBuilderAction: ViewBuilder.() -> Unit) = KNode(semanticsProvider, viewBuilderAction)
}