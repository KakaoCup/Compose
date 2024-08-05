package io.github.kakaocup.compose.node.assertion

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import io.github.kakaocup.compose.utilities.ColorAssertionsUtils

interface BackgroundAssertions : NodeAssertions {
    val backgroundColorSemanticsPropertyKey: SemanticsPropertyKey<Any>
    val backgroundShapeSemanticsPropertyKey: SemanticsPropertyKey<Shape>

    /**
     * Asserts that the compose view background color contains the given [color].
     *
     * Throws [AssertionError] if the compose view background color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [backgroundColorSemanticsPropertyKey] modifier.
     */
    fun assertBackgroundColorEquals(color: Color) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(ColorAssertionsUtils.hasColor(color, backgroundColorSemanticsPropertyKey))
        }
    }

    /**
     * Asserts that the compose view background color contains the given [color].
     *
     * Throws [AssertionError] if the compose view background color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [backgroundColorSemanticsPropertyKey] modifier.
     * Throws [IllegalArgumentException] if the color value is incorrect.
     */
    fun assertBackgroundColorEquals(color: String) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(ColorAssertionsUtils.hasColor(color, backgroundColorSemanticsPropertyKey))
        }
    }

    /**
     * Asserts that the compose view background color contains the given [color].
     *
     * Throws [AssertionError] if the compose view background color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [backgroundColorSemanticsPropertyKey] modifier.
     */
    fun assertBackgroundColorEquals(color: Long) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(ColorAssertionsUtils.hasColor(color, backgroundColorSemanticsPropertyKey))
        }
    }

    /**
     * Asserts that the compose view background shape contains the given [shape].
     *
     * Throws [AssertionError] if the compose view background shape value is not equal to `shape`.
     * Throws [IllegalStateException] if the compose view does not contain the [backgroundShapeSemanticsPropertyKey] modifier.
     */
    fun assertBackgroundShapeEquals(shape: Shape) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(hasShape(shape, backgroundShapeSemanticsPropertyKey))
        }
    }

    private fun hasShape(expectedShape: Shape, semanticsPropertyKey: SemanticsPropertyKey<*>): SemanticsMatcher = SemanticsMatcher(
        "The shape is expected to be $expectedShape, but the actual shape is different"
    ) { node ->
        val actualShape = node.config.getOrNull(semanticsPropertyKey)
            ?: error("Compose view does not contain $semanticsPropertyKey modifier")

        return@SemanticsMatcher actualShape == expectedShape
    }
}
