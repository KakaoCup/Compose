package io.github.kakaocup.compose.node

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider

open class KNode : BaseNode<KNode> {

    internal constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        userMatcher: UserMatcher,
        parentMatcher: SemanticsMatcher? = null,
    ) : super(semanticsProvider, userMatcher, parentMatcher)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        viewBuilderAction: ViewBuilder.() -> Unit,
    ) : super(semanticsProvider, viewBuilderAction)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider,
        userMatcher: UserMatcher,
    ) : super(semanticsProvider, userMatcher)
}