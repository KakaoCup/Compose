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

val BackgroundColorSemanticKey = SemanticsPropertyKey<Any>("BackgroundColor")
val BackgroundShapeSemanticKey = SemanticsPropertyKey<Shape>("BackgroundShape")
val BackgroundBrushSemanticKey = SemanticsPropertyKey<Brush>("BackgroundBrush")
val BackgroundAlphaSemanticKey = SemanticsPropertyKey<Float>("BackgroundAlpha")

var SemanticsPropertyReceiver.backgroundColor by BackgroundColorSemanticKey
var SemanticsPropertyReceiver.backgroundShape by BackgroundShapeSemanticKey
var SemanticsPropertyReceiver.backgroundBrush by BackgroundBrushSemanticKey
var SemanticsPropertyReceiver.backgroundAlpha by BackgroundAlphaSemanticKey

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
