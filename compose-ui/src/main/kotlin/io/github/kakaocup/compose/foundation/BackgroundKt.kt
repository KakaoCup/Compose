package io.github.kakaocup.compose.foundation

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.semantics
import io.github.kakaocup.compose.semantics.backgroundAlpha
import io.github.kakaocup.compose.semantics.backgroundBrush
import io.github.kakaocup.compose.semantics.backgroundColor
import io.github.kakaocup.compose.semantics.backgroundShape

fun Modifier.background(
    color: Color,
    shape: Shape = RectangleShape
): Modifier {
    return this then
            background(color, shape) then
            semantics { this.backgroundColor = color } then
            semantics { this.backgroundShape = shape }
}

fun Modifier.background(
    brush: Brush,
    shape: Shape = RectangleShape,
    @FloatRange(from = 0.0, to = 1.0)
    alpha: Float = 1.0f
): Modifier {
    return this then
            background(brush, shape, alpha) then
            semantics { this.backgroundBrush = brush } then
            semantics { this.backgroundShape = shape } then
            semantics { this.backgroundAlpha = alpha }
}
