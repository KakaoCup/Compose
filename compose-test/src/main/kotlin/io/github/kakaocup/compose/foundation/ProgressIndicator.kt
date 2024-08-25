package io.github.kakaocup.compose.foundation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.unit.Dp
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.semantics.BackgroundColorSemanticKey
import io.github.kakaocup.compose.semantics.ColorSemanticKey
import io.github.kakaocup.compose.semantics.ProgressSemanticKey
import io.github.kakaocup.compose.semantics.StrokeCapSemanticKey
import io.github.kakaocup.compose.semantics.StrokeWidthSemanticKey

open class KProgressIndicatorNode : BaseNode<KProgressIndicatorNode> {
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
     * Asserts that the progress is equal the given [progress].
     *
     * Throws [AssertionError] if the indicator progress is not equal to `progress`.
     * Throws [IllegalStateException] if the compose view does not contain the [ProgressSemanticKey] modifier.
     */
    fun assertProgressEquals(progress: Float) {
        assertHasProperty(
            progress,
            ProgressSemanticKey,
            "progress",
        )
    }

    /**
     * Asserts that the color is equal the given [color].
     *
     * Throws [AssertionError] if the indicator color is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [ColorSemanticKey] modifier.
     */
    fun assertColorEquals(color: Color) {
        assertHasProperty(
            color,
            ColorSemanticKey,
            "color",
        )
    }

    /**
     * Asserts that the stroke width is equal the given [strokeWidth].
     *
     * Throws [AssertionError] if the indicator stroke width is not equal to `strokeWidth`.
     * Throws [IllegalStateException] if the compose view does not contain the [StrokeWidthSemanticKey] modifier.
     */
    fun assertStrokeWidthEquals(strokeWidth: Dp) {
        assertHasProperty(
            strokeWidth,
            StrokeWidthSemanticKey,
            "stroke width",
        )
    }

    /**
     * Asserts that the stroke cap is equal the given [strokeCap].
     *
     * Throws [AssertionError] if the indicator stroke cap is not equal to `strokeCap`.
     * Throws [IllegalStateException] if the compose view does not contain the [StrokeCapSemanticKey] modifier.
     */
    fun assertStrokeCapEquals(strokeCap: StrokeCap) {
        assertHasProperty(
            strokeCap,
            StrokeCapSemanticKey,
            "stroke cap",
        )
    }

    /**
     * Asserts that the background color is equal the given [color].
     *
     * Throws [AssertionError] if the indicator background color is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [BackgroundColorSemanticKey] modifier.
     */
    fun assertBackgroundColorEquals(color: Color) {
        assertHasProperty(
            color,
            BackgroundColorSemanticKey,
            "background color",
        )
    }

    /**
     * Asserts that the stroke cap is equal the given [strokeCap].
     *
     * Throws [AssertionError] if the indicator stroke cap is not equal to `strokeCap`.
     * Throws [IllegalStateException] if the compose view does not contain the [StrokeCapSemanticKey] modifier.
     */
    fun assertBackgroundColorEquals(strokeCap: StrokeCap) {
        assertHasProperty(
            strokeCap,
            StrokeCapSemanticKey,
            "stroke cap",
        )
    }
}
