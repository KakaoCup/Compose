package io.github.kakaocup.compose.node.element

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.KakaoCompose
import io.github.kakaocup.compose.node.core.ComposeMarker
import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.builder.ViewBuilder

@Suppress("UNCHECKED_CAST")
@ComposeMarker
open class ComposeScreen<out T : ComposeScreen<T>> : BaseNode<T> {

    internal constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
        nodeMatcher: NodeMatcher,
        parentNode: ComposeScreen<T>? = null,
    ) : super(semanticsProvider, nodeMatcher, parentNode)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
        viewBuilderAction: ViewBuilder.() -> Unit,
    ) : super(semanticsProvider, viewBuilderAction)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
        nodeMatcher: NodeMatcher = NodeMatcher(
            matcher = SemanticsMatcher(
                description = "Empty matcher",
                matcher = { true }
            )
        ),
    ) : super(semanticsProvider, nodeMatcher)

    fun onNode(viewBuilderAction: ViewBuilder.() -> Unit) = KNode(
        requireNotNull(
            semanticsProvider ?: KakaoCompose.Global.semanticsProvider
        ) { "SemanticsProvider not is null: Provide via constructor or use KakaoComposeTestRule" },
        viewBuilderAction,
    )

    companion object {
        inline fun <reified T : ComposeScreen<T>> onComposeScreen(
            semanticsProvider: SemanticsNodeInteractionsProvider,
            noinline function: T.() -> Unit
        ): T = T::class.java
            .getDeclaredConstructor(
                SemanticsNodeInteractionsProvider::class.java
            )
            .newInstance(semanticsProvider)
            .apply { this(function) }

        inline fun <reified T : ComposeScreen<T>> onComposeScreen(
            noinline function: T.() -> Unit
        ): T =
            T::class.java.getDeclaredConstructor()
                .newInstance()
                .apply { this(function) }

    }
}
