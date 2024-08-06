package io.github.kakaocup.compose.semantics

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

val TextColorSemanticKey = SemanticsPropertyKey<Color>("TextColor")

var SemanticsPropertyReceiver.textColor by TextColorSemanticKey