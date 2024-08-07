package io.github.kakaocup.compose.semantics

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

val IconImageContentSemanticKey = SemanticsPropertyKey<Any>("IconImageContent")
val IconTintColorSemanticKey = SemanticsPropertyKey<Color>("IconTintColor")

var SemanticsPropertyReceiver.tintColor by IconTintColorSemanticKey
var SemanticsPropertyReceiver.imageContent by IconImageContentSemanticKey
