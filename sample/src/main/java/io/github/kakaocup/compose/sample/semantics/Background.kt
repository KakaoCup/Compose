package io.github.kakaocup.compose.sample.semantics

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics

val BackgroundColorSemanticKey = SemanticsPropertyKey<Any>("BackgroundColor")
var SemanticsPropertyReceiver.backgroundColor by BackgroundColorSemanticKey
fun Modifier.backgroundColorSemantic(color: Color): Modifier {
    return semantics { this.backgroundColor = color }
}

val BackgroundShapeSemanticKey = SemanticsPropertyKey<Shape>("BackgroundShape")
var SemanticsPropertyReceiver.backgroundShape by BackgroundShapeSemanticKey
fun Modifier.backgroundShapeSemantic(shape: Shape): Modifier {
    return semantics { this.backgroundShape = shape }
}
