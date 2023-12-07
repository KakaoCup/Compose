package io.github.kakaocup.compose.sample.semantics

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics

val TintColorSemanticKey = SemanticsPropertyKey<Color>("TintColor")
var SemanticsPropertyReceiver.tintColor by TintColorSemanticKey
fun Modifier.tintColorSemantic(color: Color): Modifier {
    return semantics { tintColor = color }
}
