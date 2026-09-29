package io.github.kakaocup.compose.node.assertion

import androidx.annotation.StringRes
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import io.github.kakaocup.compose.utilities.getResourceString

actual interface TextResourcesNodeAssertions : NodeAssertions {
    fun assertContentDescriptionEquals(
        @StringRes vararg values: Int
    ) {
        assertContentDescriptionEquals(values = values.map(::getResourceString).toTypedArray())
    }

    fun assertContentDescriptionContains(
        @StringRes value: Int,
        substring: Boolean = false,
        ignoreCase: Boolean = false
    ) {
        assertContentDescriptionContains(getResourceString(value), substring, ignoreCase)
    }

    fun assertTextEquals(
        @StringRes vararg values: Int,
        includeEditableText: Boolean = true
    ) {
        assertTextEquals(
            values = values.map(::getResourceString).toTypedArray(),
            includeEditableText = includeEditableText
        )
    }

    fun assertTextContains(
        @StringRes value: Int,
        substring: Boolean = false,
        ignoreCase: Boolean = false
    ) {
        assertTextContains(getResourceString(value), substring, ignoreCase)
    }

    fun assertValueEquals(@StringRes value: Int) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assertValueEquals(getResourceString(value))
        }
    }
}
