package io.github.kakaocup.compose.node.assertion

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.text.TextLayoutResult
import io.github.kakaocup.compose.utilities.ColorAssertionsUtils

interface TextColorAssertions : NodeAssertions {
    val textColorSemanticsPropertyKey: SemanticsPropertyKey<Color>

    /**
     * Asserts that the text color contains the given [color].
     *
     * Throws [AssertionError] if the text color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [textColorSemanticsPropertyKey] modifier.
     */
    fun assertTextColorEquals(color: Color) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(hasTextColor(color))
        }
    }

    /**
     * Asserts that text color contains the given [color].
     *
     * Throws [AssertionError] if the text color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [textColorSemanticsPropertyKey] modifier.
     * Throws [IllegalArgumentException] if the color value is incorrect.
     */
    fun assertTextColorEquals(color: String) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(hasTextColor(color))
        }
    }

    /**
     * Asserts that the text color contains the given [color].
     *
     * Throws [AssertionError] if the text color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [textColorSemanticsPropertyKey] modifier.
     */
    fun assertTextColorEquals(color: Long) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(hasTextColor(color))
        }
    }

    private fun hasTextColor(expectedColor: Color): SemanticsMatcher = SemanticsMatcher(
        "${SemanticsProperties.Text.name} is of color '$expectedColor'"
    ) { node ->
        return@SemanticsMatcher node.hasTextColor(expectedColor = expectedColor)
    }

    private fun hasTextColor(expectedColor: Long): SemanticsMatcher = SemanticsMatcher(
        "${SemanticsProperties.Text.name} is of color '$expectedColor'"
    ) { node ->
        return@SemanticsMatcher node.hasTextColor(expectedColor = Color(expectedColor))
    }

    private fun hasTextColor(expectedColor: String): SemanticsMatcher = SemanticsMatcher(
        "${SemanticsProperties.Text.name} is of color '$expectedColor'"
    ) { node ->
        return@SemanticsMatcher node.hasTextColor(expectedColor = ColorAssertionsUtils.getComposeColor(expectedColor))
    }

    private fun SemanticsNode.hasTextColor(expectedColor: Color): Boolean {
        val textLayoutResults = mutableListOf<TextLayoutResult>()
        config.getOrNull(SemanticsActions.GetTextLayoutResult)?.action?.invoke(textLayoutResults)

        val actualColorOfStyle = textLayoutResults.firstOrNull()?.layoutInput?.style?.color ?: Color.Unspecified
        val actualColorOfSemantic = config.getOrNull(textColorSemanticsPropertyKey) ?: Color.Unspecified

        /**
         * look at how the overrideColor parameter is used
         * @see [androidx.compose.foundation.text.modifiers.TextStringSimpleNode.draw]
         * @see [androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.draw]
         */
        val actualColor = when {
            actualColorOfSemantic.isSpecified -> actualColorOfSemantic
            actualColorOfStyle.isSpecified -> actualColorOfStyle
            else -> Color.Black
        }

        return actualColor == expectedColor
    }
}
