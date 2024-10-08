package io.github.kakaocup.compose.foundation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.semantics.TextColorSemanticKey
import io.github.kakaocup.compose.semantics.FontFamilySemanticKey
import io.github.kakaocup.compose.semantics.FontSizeSemanticKey
import io.github.kakaocup.compose.semantics.FontStyleSemanticKey
import io.github.kakaocup.compose.semantics.FontWeightSemanticKey
import io.github.kakaocup.compose.semantics.LetterSpacingSizeSemanticKey
import io.github.kakaocup.compose.semantics.LineHeightSemanticKey
import io.github.kakaocup.compose.semantics.MaxLinesSemanticKey
import io.github.kakaocup.compose.semantics.MinLinesSemanticKey
import io.github.kakaocup.compose.semantics.OverflowSemanticKey
import io.github.kakaocup.compose.semantics.SoftWrapSemanticKey
import io.github.kakaocup.compose.semantics.TextAlignSemanticKey
import io.github.kakaocup.compose.semantics.TextDecorationSemanticKey

open class KTextNode : BaseNode<KTextNode> {
    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
        nodeMatcher: NodeMatcher,
        parentNode: BaseNode<*>? = null,
    ) : super(semanticsProvider, nodeMatcher, parentNode)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
        viewBuilderAction: ViewBuilder.() -> Unit,
    ) : super(semanticsProvider, viewBuilderAction)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
        nodeMatcher: NodeMatcher,
    ) : super(semanticsProvider, nodeMatcher)

    /**
     * Asserts that the text color contains the given [color].
     *
     * Throws [AssertionError] if the text color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [TextColorSemanticKey] modifier.
     */
    fun assertTextColorEquals(color: Color) {
        assertHasProperty(
            color,
            TextColorSemanticKey,
            "text color"
        )
    }

    /**
     * Asserts that text color contains the given [color].
     *
     * Throws [AssertionError] if the text color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [TextColorSemanticKey] modifier.
     * Throws [IllegalArgumentException] if the color value is incorrect.
     */
    fun assertTextColorEquals(color: String) {
        assertHasProperty(
            Color(android.graphics.Color.parseColor(color)),
            TextColorSemanticKey,
            "text color",
            )
    }

    /**
     * Asserts that the text color contains the given [color].
     *
     * Throws [AssertionError] if the text color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [TextColorSemanticKey] modifier.
     */
    fun assertTextColorEquals(color: Long) {
        assertHasProperty(
            Color(color),
            TextColorSemanticKey,
            "text color",
        )
    }

    /**
     * Asserts that the font size is equal to the given [fontSize].
     *
     * Throws [AssertionError] if the font size value is not equal to `fontSize`.
     * Throws [IllegalStateException] if the compose view does not contain the [FontSizeSemanticKey] modifier.
     */
    fun assertFontSizeEquals(fontSize: TextUnit) {
        assertHasProperty(
            fontSize,
            FontSizeSemanticKey,
            "font size",
        )
    }

    /**
     * Asserts that the font style is equal to the given [fontStyle].
     *
     * Throws [AssertionError] if the font size value is not equal to `fontStyle`.
     * Throws [IllegalStateException] if the compose view does not contain the [FontStyleSemanticKey] modifier.
     */
    fun assertFontStyleEquals(fontStyle: FontStyle) {
        assertHasProperty(
            fontStyle,
            FontStyleSemanticKey,
            "font style",
        )
    }

    /**
     * Asserts that the font weight is equal to the given [fontWeight].
     *
     * Throws [AssertionError] if the font weight value is not equal to `fontWeight`.
     * Throws [IllegalStateException] if the compose view does not contain the [FontWeightSemanticKey] modifier.
     */
    fun assertFontWeightEquals(fontWeight: FontWeight) {
        assertHasProperty(
            fontWeight,
            FontWeightSemanticKey,
            "font weight",
        )
    }

    /**
     * Asserts that the font family is equal to the given [fontFamily].
     *
     * Throws [AssertionError] if the font family value is not equal to `fontFamily`.
     * Throws [IllegalStateException] if the compose view does not contain the [FontFamilySemanticKey] modifier.
     */
    fun assertFontFamilyEquals(fontFamily: FontFamily) {
        assertHasProperty(
            fontFamily,
            FontFamilySemanticKey,
            "font family",
        )
    }

    /**
     * Asserts that the letter spacing is equal to the given [letterSpacing].
     *
     * Throws [AssertionError] if the letter spacing value is not equal to `letterSpacing`.
     * Throws [IllegalStateException] if the compose view does not contain the [LetterSpacingSizeSemanticKey] modifier.
     */
    fun assertLetterSpacingEquals(letterSpacing: TextUnit) {
        assertHasProperty(
            letterSpacing,
            LetterSpacingSizeSemanticKey,
            "letter spacing",
        )
    }

    /**
     * Asserts that the text decoration is equal to the given [textDecoration].
     *
     * Throws [AssertionError] if the text decoration value is not equal to `textDecoration`.
     * Throws [IllegalStateException] if the compose view does not contain the [TextDecorationSemanticKey] modifier.
     */
    fun assertTextDecorationEquals(textDecoration: TextDecoration) {
        assertHasProperty(
            textDecoration,
            TextDecorationSemanticKey,
            "text decoration",
        )
    }

    /**
     * Asserts that the text align is equal to the given [textAlign].
     *
     * Throws [AssertionError] if the text align is not equal to `textAlign`.
     * Throws [IllegalStateException] if the compose view does not contain the [TextAlignSemanticKey] modifier.
     */
    fun assertTextAlignEquals(textAlign: TextAlign) {
        assertHasProperty(
            textAlign,
            TextAlignSemanticKey,
            "text align",
        )
    }

    /**
     * Asserts that the line height is equal the given [lineHeight].
     *
     * Throws [AssertionError] if the line height value is not equal to `lineHeight`.
     * Throws [IllegalStateException] if the compose view does not contain the [LineHeightSemanticKey] modifier.
     */
    fun assertLineHeightEquals(lineHeight: TextUnit) {
        assertHasProperty(
            lineHeight,
            LineHeightSemanticKey,
            "line height",
        )
    }

    /**
     * Asserts that the text overflow equal the given [overflow].
     *
     * Throws [AssertionError] if the text overflow value is not equal to `overflow`.
     * Throws [IllegalStateException] if the compose view does not contain the [OverflowSemanticKey] modifier.
     */
    fun assertOverflowEquals(overflow: TextOverflow) {
        assertHasProperty(
            overflow,
            OverflowSemanticKey,
            "text overflow",
        )
    }

    /**
     * Asserts that the soft wrap is equal the given [softWrap].
     *
     * Throws [AssertionError] if the soft wrap is not equal to `softWrap`.
     * Throws [IllegalStateException] if the compose view does not contain the [SoftWrapSemanticKey] modifier.
     */
    fun assertSoftWrapEquals(softWrap: Boolean) {
        assertHasProperty(
            softWrap,
            SoftWrapSemanticKey,
            "soft wrap",
        )
    }

    /**
     * Asserts that the max lines is equal the given [maxLines].
     *
     * Throws [AssertionError] if the text max lines is not equal to `maxLines`.
     * Throws [IllegalStateException] if the compose view does not contain the [MaxLinesSemanticKey] modifier.
     */
    fun assertMaxLinesEquals(maxLines: Int) {
        assertHasProperty(
            maxLines,
            MaxLinesSemanticKey,
            "max lines",
        )
    }

    /**
     * Asserts that the min lines is equal the given [minLines].
     *
     * Throws [AssertionError] if the text min lines is not equal to `minLines`.
     * Throws [IllegalStateException] if the compose view does not contain the [MinLinesSemanticKey] modifier.
     */
    fun assertMinLinesEquals(minLines: Int) {
        assertHasProperty(
            minLines,
            MinLinesSemanticKey,
            "min lines",
        )
    }
}
