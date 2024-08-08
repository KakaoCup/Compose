package io.github.kakaocup.compose.foundation

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.semantics.BackgroundAlphaSemanticKey
import io.github.kakaocup.compose.semantics.BackgroundBrushSemanticKey
import io.github.kakaocup.compose.semantics.BackgroundColorSemanticKey
import io.github.kakaocup.compose.semantics.BackgroundShapeSemanticKey

/**
 * Asserts that the compose view background color contains the given [color].
 *
 * Throws [AssertionError] if the compose view background color value is not equal to `color`.
 * Throws [IllegalStateException] if the compose view does not contain the [BackgroundColorSemanticKey] modifier.
 */
fun KNode.assertBackgroundColorEquals(color: Color) {
    assertHasProperty(
        color,
        BackgroundColorSemanticKey,
        "color",
        )
}

/**
 * Asserts that the compose view background color contains the given [color].
 *
 * Throws [AssertionError] if the compose view background color value is not equal to `color`.
 * Throws [IllegalStateException] if the compose view does not contain the [BackgroundColorSemanticKey] modifier.
 * Throws [IllegalArgumentException] if the color value is incorrect.
 */
fun KNode.assertBackgroundColorEquals(color: String) {
    assertHasProperty(
        Color(android.graphics.Color.parseColor(color)),
        BackgroundColorSemanticKey,
        "color",
        )
}

/**
 * Asserts that the compose view background color contains the given [color].
 *
 * Throws [AssertionError] if the compose view background color value is not equal to `color`.
 * Throws [IllegalStateException] if the compose view does not contain the [BackgroundColorSemanticKey] modifier.
 */
fun KNode.assertBackgroundColorEquals(color: Long) {
    assertHasProperty(
        Color(color),
        BackgroundColorSemanticKey,
        "color",
        )
}

/**
 * Asserts that the compose view background shape contains the given [shape].
 *
 * Throws [AssertionError] if the compose view background shape value is not equal to `shape`.
 * Throws [IllegalStateException] if the compose view does not contain the [BackgroundShapeSemanticKey] modifier.
 */
fun KNode.assertBackgroundShapeEquals(shape: Shape) {
    assertHasProperty(
        shape,
        BackgroundShapeSemanticKey,
        "shape",
        )
}

/**
 * Asserts that the compose view background brush contains the given [brush].
 *
 * Throws [AssertionError] if the compose view background brush value is not equal to `brush`.
 * Throws [IllegalStateException] if the compose view does not contain the [BackgroundBrushSemanticKey] modifier.
 */
fun KNode.assertBackgroundBrushEquals(brush: Brush) {
    assertHasProperty(
        brush,
        BackgroundBrushSemanticKey,
        "brush",
        )
}

/**
 * Asserts that the compose view background brush contains the given [alpha].
 *
 * Throws [AssertionError] if the compose view background alpha value is not equal to `alpha`.
 * Throws [IllegalStateException] if the compose view does not contain the [BackgroundAlphaSemanticKey] modifier.
 */
fun KNode.assertBackgroundAlphaEquals(alpha: Float) {
    assertHasProperty(
        alpha,
        BackgroundAlphaSemanticKey,
        "alpha",
        )
}
