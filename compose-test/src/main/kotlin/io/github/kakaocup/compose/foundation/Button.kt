package io.github.kakaocup.compose.foundation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.semantics.BorderSemanticKey
import io.github.kakaocup.compose.semantics.ContentPaddingSemanticKey
import io.github.kakaocup.compose.semantics.ShapeSemanticKey

open class KButtonNode : BaseNode<KButtonNode> {
    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
        nodeMatcher: NodeMatcher,
        parentNode: BaseNode<*>? = null,
    ) : super(semanticsProvider, nodeMatcher, parentNode)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
        viewBuilderAction: ViewBuilder.() -> Unit,
    ) : super(semanticsProvider, viewBuilderAction)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
        nodeMatcher: NodeMatcher,
    ) : super(semanticsProvider, nodeMatcher)

    /**
     * Asserts that the shape is equal the given [shape].
     *
     * Throws [AssertionError] if the button shape is not equal to `shape`.
     * Throws [IllegalStateException] if the compose view does not contain the [ShapeSemanticKey] modifier.
     */
    fun assertShapeEquals(shape: Shape) {
        assertHasProperty(
            shape,
            ShapeSemanticKey,
            "shape",
        )
    }

    /**
     * Asserts that the border stroke is equal the given [borderStroke].
     *
     * Throws [AssertionError] if the button border stroke is not equal to `borderStroke`.
     * Throws [IllegalStateException] if the compose view does not contain the [ShapeSemanticKey] modifier.
     */
    fun assertBorderStrokeEquals(borderStroke: BorderStroke) {
        assertHasProperty(
            borderStroke,
            BorderSemanticKey,
            "border stroke",
        )
    }



    /**
     * Asserts that the content padding is equal the given [contentPadding].
     *
     * Throws [AssertionError] if the content padding is not equal to `contentPadding`.
     * Throws [IllegalStateException] if the compose view does not contain the [ContentPaddingSemanticKey] modifier.
     */
    fun assertPaddingValuesEquals(contentPadding: PaddingValues) {
        assertHasProperty(
            contentPadding,
            ContentPaddingSemanticKey,
            "content padding",
        )
    }
}
