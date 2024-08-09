package io.github.kakaocup.compose.semantics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

val ButtonShapeSemanticKey = SemanticsPropertyKey<Shape>("ButtonShape")
val ButtonBorderStrokeSemanticKey = SemanticsPropertyKey<BorderStroke?>("ButtonBorderStroke")
val ButtonPaddingValuesSemanticKey = SemanticsPropertyKey<PaddingValues>("ButtonPaddingValues")

var SemanticsPropertyReceiver.shape by ButtonShapeSemanticKey
var SemanticsPropertyReceiver.border by ButtonBorderStrokeSemanticKey
var SemanticsPropertyReceiver.contentPadding by ButtonPaddingValuesSemanticKey
