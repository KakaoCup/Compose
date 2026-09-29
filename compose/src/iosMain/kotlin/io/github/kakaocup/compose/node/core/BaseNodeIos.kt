package io.github.kakaocup.compose.node.core

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.element.KNode

/**
 * iOS lacks the reflection used on Android to construct arbitrary [BaseNode] subtypes.
 * For the common case where consumers want a plain [KNode] (i.e. `val x: KNode = child { … }`)
 * we instantiate a [KNode] and rely on the reified cast at the call site.
 *
 * If a caller picks a non-[KNode] subtype on iOS, the reified cast throws
 * [ClassCastException] with a clear message — declare your screens' children as
 * [KNode] (or construct the custom subclass directly) on iOS.
 */
@PublishedApi
internal actual inline fun <reified N> createChildNode(
    semanticsProvider: SemanticsNodeInteractionsProvider,
    nodeMatcher: NodeMatcher,
    parentNode: BaseNode<*>,
): N {
    val node = KNode(
        semanticsProvider = semanticsProvider,
        nodeMatcher = nodeMatcher,
        parentNode = parentNode,
    )
    return node as N
}
