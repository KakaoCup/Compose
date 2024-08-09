package io.github.kakaocup.compose.semantics

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

val BackgroundShapeSemanticKey = SemanticsPropertyKey<Shape>("BackgroundShape")
val BackgroundBrushSemanticKey = SemanticsPropertyKey<Brush>("BackgroundBrush")
val BackgroundAlphaSemanticKey = SemanticsPropertyKey<Float>("BackgroundAlpha")

var SemanticsPropertyReceiver.backgroundShape by BackgroundShapeSemanticKey
var SemanticsPropertyReceiver.backgroundBrush by BackgroundBrushSemanticKey
var SemanticsPropertyReceiver.backgroundAlpha by BackgroundAlphaSemanticKey
