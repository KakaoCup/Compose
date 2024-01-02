package io.github.kakaocup.compose.node.assertion

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import io.github.kakaocup.compose.utilities.getComposeColor

interface TintColorAssertions : NodeAssertions {
    val tintColorSemanticsPropertyKey: SemanticsPropertyKey<Color>

    /**
     * Asserts that the compose view tint color contains the given [color].
     *
     * Throws [AssertionError] if the compose view tint color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [tintColorSemanticsPropertyKey] modifier.
     */
    fun assertTintColorEquals(color: Color) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) { assert(hasColor(color)) }
    }

    /**
     * Asserts that the compose view tint color contains the given [color].
     *
     * Throws [AssertionError] if the compose view tint color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [tintColorSemanticsPropertyKey] modifier.
     * Throws [IllegalArgumentException] if the color value is incorrect.
     */
    fun assertTintColorEquals(color: String) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) { assert(hasColor(color)) }
    }

    /**
     * Asserts that the compose view tint color contains the given [color].
     *
     * Throws [AssertionError] if the compose view tint color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [tintColorSemanticsPropertyKey] modifier.
     */
    fun assertTintColorEquals(color: Long) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) { assert(hasColor(color)) }
    }

    private fun hasColor(expectedColor: Color): SemanticsMatcher = SemanticsMatcher(
        "The color is expected to be $expectedColor, but the actual color is different"
    ) { node ->
        val actualColor = node.config.getOrNull(tintColorSemanticsPropertyKey)
            ?: error("Compose view does not contain $tintColorSemanticsPropertyKey modifier")

        return@SemanticsMatcher actualColor == expectedColor
    }

    private fun hasColor(expectedColor: String): SemanticsMatcher = SemanticsMatcher(
        "The color is expected to be $expectedColor, but the actual color is different"
    ) { node ->
        val actualColor = node.config.getOrNull(tintColorSemanticsPropertyKey)
            ?: error("Compose view does not contain $tintColorSemanticsPropertyKey modifier")

        return@SemanticsMatcher actualColor == getComposeColor(expectedColor)
    }

    private fun hasColor(expectedColor: Long): SemanticsMatcher = SemanticsMatcher(
        "The color is expected to be $expectedColor, but the actual color is different"
    ) { node ->
        val actualColor = node.config.getOrNull(tintColorSemanticsPropertyKey)
            ?: error("Compose view does not contain $tintColorSemanticsPropertyKey modifier")

        return@SemanticsMatcher actualColor == Color(expectedColor)
    }
}
