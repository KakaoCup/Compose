package io.github.kakaocup.compose.semantics

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

val TextColorSemanticKey = SemanticsPropertyKey<Color>("TextColor")
val TextFontSizeSemanticKey = SemanticsPropertyKey<TextUnit>("TextFontSize")
val TextFontStyleSemanticKey = SemanticsPropertyKey<FontStyle?>("TextFontStyle")
val TextFontWeightSemanticKey = SemanticsPropertyKey<FontWeight?>("TextFontWeight")
val TextFontFamilySemanticKey = SemanticsPropertyKey<FontFamily?>("TextFontFamily")
val TextLetterSpacingSizeSemanticKey = SemanticsPropertyKey<TextUnit>("TextLetterSpacing")
val TextTextDecorationSemanticKey = SemanticsPropertyKey<TextDecoration?>("TextTextDecoration")
val TextTextAlignSemanticKey = SemanticsPropertyKey<TextAlign?>("TextTextAlign")
val TextLineHeightSemanticKey = SemanticsPropertyKey<TextUnit>("TextLineHeight")
val TextOverflowSemanticKey = SemanticsPropertyKey<TextOverflow>("TextOverflow")
val TextSoftWrapSemanticKey = SemanticsPropertyKey<Boolean>("TextSoftWrap")
val TextMaxLinesSemanticKey = SemanticsPropertyKey<Int>("TextMaxLines")
val TextMinLinesSemanticKey = SemanticsPropertyKey<Int>("TextMinLines")

var SemanticsPropertyReceiver.textColor by TextColorSemanticKey
var SemanticsPropertyReceiver.fontSize by TextFontSizeSemanticKey
var SemanticsPropertyReceiver.fontStyle by TextFontStyleSemanticKey
var SemanticsPropertyReceiver.fontWeight by TextFontWeightSemanticKey
var SemanticsPropertyReceiver.fontFamily by TextFontFamilySemanticKey
var SemanticsPropertyReceiver.letterSpacing by TextLetterSpacingSizeSemanticKey
var SemanticsPropertyReceiver.textDecoration by TextTextDecorationSemanticKey
var SemanticsPropertyReceiver.textAlign by TextTextAlignSemanticKey
var SemanticsPropertyReceiver.lineHeight by TextLineHeightSemanticKey
var SemanticsPropertyReceiver.overflow by TextOverflowSemanticKey
var SemanticsPropertyReceiver.softWrap by TextSoftWrapSemanticKey
var SemanticsPropertyReceiver.maxLines by TextMaxLinesSemanticKey
var SemanticsPropertyReceiver.minLines by TextMinLinesSemanticKey
