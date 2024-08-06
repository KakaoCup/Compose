package io.github.kakaocup.compose.foundation

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import io.github.kakaocup.compose.node.assertion.NodeAssertions
import io.github.kakaocup.compose.node.element.KNode

/**
 * Asserts that the compose view background color contains the given [color].
 *
 * Throws [AssertionError] if the compose view background color value is not equal to `color`.
 * Throws [IllegalStateException] if the compose view does not contain the [BackgroundColorSemanticKey] modifier.
 */
fun KNode.assertBackgroundColorEquals(color: Color) {
    delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
        assert(hasProperty(color, BackgroundColorSemanticKey, "color"))
    }
}

/**
 * Asserts that the compose view background color contains the given [color].
 *
 * Throws [AssertionError] if the compose view background color value is not equal to `color`.
 * Throws [IllegalStateException] if the compose view does not contain the [BackgroundColorSemanticKey] modifier.
 * Throws [IllegalArgumentException] if the color value is incorrect.
 */
fun KNode.assertBackgroundColorEquals(color: String) {
    delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
        assert(
            hasProperty(
                Color(android.graphics.Color.parseColor(color)),
                BackgroundColorSemanticKey,
                "color"
            )
        )
    }
}

/**
 * Asserts that the compose view background color contains the given [color].
 *
 * Throws [AssertionError] if the compose view background color value is not equal to `color`.
 * Throws [IllegalStateException] if the compose view does not contain the [BackgroundColorSemanticKey] modifier.
 */
fun KNode.assertBackgroundColorEquals(color: Long) {
    delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
        assert(hasProperty(Color(color), BackgroundColorSemanticKey, "color"))
    }
}

/**
 * Asserts that the compose view background shape contains the given [shape].
 *
 * Throws [AssertionError] if the compose view background shape value is not equal to `shape`.
 * Throws [IllegalStateException] if the compose view does not contain the [BackgroundShapeSemanticKey] modifier.
 */
fun KNode.assertBackgroundShapeEquals(shape: Shape) {
    delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
        assert(hasProperty(shape, BackgroundShapeSemanticKey, "shape"))
    }
}

/**
 * Asserts that the compose view background brush contains the given [brush].
 *
 * Throws [AssertionError] if the compose view background brush value is not equal to `brush`.
 * Throws [IllegalStateException] if the compose view does not contain the [BackgroundBrushSemanticKey] modifier.
 */
fun KNode.assertBackgroundBrushEquals(brush: Brush) {
    delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
        assert(hasProperty(brush, BackgroundBrushSemanticKey, "brush"))
    }
}

/**
 * Asserts that the compose view background brush contains the given [alpha].
 *
 * Throws [AssertionError] if the compose view background alpha value is not equal to `alpha`.
 * Throws [IllegalStateException] if the compose view does not contain the [BackgroundAlphaSemanticKey] modifier.
 */
fun KNode.assertBackgroundAlphaEquals(alpha: Float) {
    delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
        assert(hasProperty(alpha, BackgroundAlphaSemanticKey, "alpha"))
    }
}
