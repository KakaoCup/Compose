package io.github.kakaocup.compose.semantics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

val BackgroundColorSemanticKey = SemanticsPropertyKey<Color>("BackgroundColor")
var SemanticsPropertyReceiver.backgroundColor by BackgroundColorSemanticKey

val BorderSemanticKey = SemanticsPropertyKey<BorderStroke?>("Border")
var SemanticsPropertyReceiver.border by BorderSemanticKey

val ColorSemanticKey = SemanticsPropertyKey<Color>("Color")
var SemanticsPropertyReceiver.color by ColorSemanticKey

val ContentPaddingSemanticKey = SemanticsPropertyKey<PaddingValues>("ContentPadding")
var SemanticsPropertyReceiver.contentPadding by ContentPaddingSemanticKey

val FontSizeSemanticKey = SemanticsPropertyKey<TextUnit>("FontSize")
var SemanticsPropertyReceiver.fontSize by FontSizeSemanticKey

val FontStyleSemanticKey = SemanticsPropertyKey<FontStyle?>("FontStyle")
var SemanticsPropertyReceiver.fontStyle by FontStyleSemanticKey

val FontWeightSemanticKey = SemanticsPropertyKey<FontWeight?>("FontWeight")
var SemanticsPropertyReceiver.fontWeight by FontWeightSemanticKey

val FontFamilySemanticKey = SemanticsPropertyKey<FontFamily?>("FontFamily")
var SemanticsPropertyReceiver.fontFamily by FontFamilySemanticKey

val ImageContentSemanticKey = SemanticsPropertyKey<Any>("ImageContent")
var SemanticsPropertyReceiver.imageContent by ImageContentSemanticKey

val LetterSpacingSizeSemanticKey = SemanticsPropertyKey<TextUnit>("LetterSpacing")
var SemanticsPropertyReceiver.letterSpacing by LetterSpacingSizeSemanticKey

val LineHeightSemanticKey = SemanticsPropertyKey<TextUnit>("LineHeight")
var SemanticsPropertyReceiver.lineHeight by LineHeightSemanticKey

val MaxLinesSemanticKey = SemanticsPropertyKey<Int>("MaxLines")
var SemanticsPropertyReceiver.maxLines by MaxLinesSemanticKey

val MinLinesSemanticKey = SemanticsPropertyKey<Int>("MinLines")
var SemanticsPropertyReceiver.minLines by MinLinesSemanticKey

val OverflowSemanticKey = SemanticsPropertyKey<TextOverflow>("Overflow")
var SemanticsPropertyReceiver.overflow by OverflowSemanticKey

val ProgressSemanticKey = SemanticsPropertyKey<Float>("Progress")
var SemanticsPropertyReceiver.progress by ProgressSemanticKey

val ShapeSemanticKey = SemanticsPropertyKey<Shape>("Shape")
var SemanticsPropertyReceiver.shape by ShapeSemanticKey

val SoftWrapSemanticKey = SemanticsPropertyKey<Boolean>("SoftWrap")
var SemanticsPropertyReceiver.softWrap by SoftWrapSemanticKey

val StrokeCapSemanticKey = SemanticsPropertyKey<StrokeCap>("StrokeCap")
var SemanticsPropertyReceiver.strokeCap by StrokeCapSemanticKey

val StrokeWidthSemanticKey = SemanticsPropertyKey<Dp>("StrokeWidth")
var SemanticsPropertyReceiver.strokeWidth by StrokeWidthSemanticKey

val TextAlignSemanticKey = SemanticsPropertyKey<TextAlign?>("TextAlign")
var SemanticsPropertyReceiver.textAlign by TextAlignSemanticKey

val TextColorSemanticKey = SemanticsPropertyKey<Color>("TextColor")
var SemanticsPropertyReceiver.textColor by TextColorSemanticKey

val TextDecorationSemanticKey = SemanticsPropertyKey<TextDecoration?>("TextDecoration")
var SemanticsPropertyReceiver.textDecoration by TextDecorationSemanticKey

val TintColorSemanticKey = SemanticsPropertyKey<Color>("TintColor")
var SemanticsPropertyReceiver.tintColor by TintColorSemanticKey
