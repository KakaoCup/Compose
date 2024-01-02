package io.github.kakaocup.compose.node.assertion

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.assert
import io.github.kakaocup.compose.utilities.ColorAssertionsUtils

interface TintColorAssertions : NodeAssertions {
    val tintColorSemanticsPropertyKey: SemanticsPropertyKey<Color>

    /**
     * Asserts that the compose view tint color contains the given [color].
     *
     * Throws [AssertionError] if the compose view tint color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [tintColorSemanticsPropertyKey] modifier.
     */
    fun assertTintColorEquals(color: Color) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(ColorAssertionsUtils.hasColor(color, tintColorSemanticsPropertyKey))
        }
    }

    /**
     * Asserts that the compose view tint color contains the given [color].
     *
     * Throws [AssertionError] if the compose view tint color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [tintColorSemanticsPropertyKey] modifier.
     * Throws [IllegalArgumentException] if the color value is incorrect.
     */
    fun assertTintColorEquals(color: String) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(ColorAssertionsUtils.hasColor(color, tintColorSemanticsPropertyKey))
        }
    }

    /**
     * Asserts that the compose view tint color contains the given [color].
     *
     * Throws [AssertionError] if the compose view tint color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [tintColorSemanticsPropertyKey] modifier.
     */
    fun assertTintColorEquals(color: Long) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(ColorAssertionsUtils.hasColor(color, tintColorSemanticsPropertyKey))
        }
    }
}
