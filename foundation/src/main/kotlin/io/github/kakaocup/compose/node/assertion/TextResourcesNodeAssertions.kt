package io.github.kakaocup.compose.node.assertion

import androidx.annotation.StringRes
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import io.github.kakaocup.compose.utilities.getResourceString

interface TextResourcesNodeAssertions : NodeAssertions {
    /**
     * Asserts that the node's content description contains exactly the given [values] and nothing else.
     *
     * Note that in merged semantics tree there can be a list of content descriptions that got merged
     * from the child nodes. Typically an accessibility tooling will decide based on its heuristics
     * which ones to announce.
     *
     * Throws [AssertionError] if the node's descriptions don't contain all items from [values], or
     * if the descriptions contain extra items that are not in [values].
     *
     * @param values List of string resources id's values to match (the order does not matter)
     * @see SemanticsProperties.ContentDescription
     */
    fun assertContentDescriptionEquals(
        @StringRes vararg values: Int
    ) {
        assertContentDescriptionEquals(values = values.map(::getResourceString).toTypedArray())
    }

    /**
     * Asserts that the node's content description contains the given [value].
     *
     * Note that in merged semantics tree there can be a list of content descriptions that got merged
     * from the child nodes. Typically an accessibility tooling will decide based on its heuristics
     * which ones to announce.
     *
     * Throws [AssertionError] if the node's value does not contain `value`, or if the node has no value
     *
     * @param value String resource id value to match as one of the items in the list of content descriptions.
     * @param substring Whether this can be satisfied as a substring match of an item in the list of
     * descriptions.
     * @param ignoreCase Whether case should be ignored.
     * @see SemanticsProperties.ContentDescription
     */
    fun assertContentDescriptionContains(
        @StringRes value: Int,
        substring: Boolean = false,
        ignoreCase: Boolean = false
    ) {
        assertContentDescriptionContains(getResourceString(value), substring, ignoreCase)
    }

    /**
     * Asserts that the node's text contains exactly the given [values] and nothing else.
     *
     * This will also search in [SemanticsProperties.EditableText] by default.
     *
     * Note that in merged semantics tree there can be a list of text items that got merged from
     * the child nodes. Typically an accessibility tooling will decide based on its heuristics which
     * ones to use.
     *
     * Throws [AssertionError] if the node's text values don't contain all items from [values], or
     * if the text values contain extra items that are not in [values].
     *
     * @param values List of string resources id's to match (the order does not matter)
     * @param includeEditableText Whether to also assert against the editable text.
     * @see SemanticsProperties.ContentDescription
     */
    fun assertTextEquals(
        @StringRes vararg values: Int,
        includeEditableText: Boolean = true
    ) {
        assertTextEquals(
            values = values.map(::getResourceString).toTypedArray(),
            includeEditableText = includeEditableText
        )
    }

    /**
     * Asserts that the node's text contains the given [value].
     *
     * This will also search in [SemanticsProperties.EditableText].
     *
     * Note that in merged semantics tree there can be a list of text items that got merged from
     * the child nodes. Typically an accessibility tooling will decide based on its heuristics which
     * ones to use.
     *
     * Throws [AssertionError] if the node's value does not contain `value`, or if the node has no value
     *
     * @param value String resource id value to match as one of the items in the list of text values.
     * @param substring Whether this can be satisfied as a substring match of an item in the list of
     * text.
     * @param ignoreCase Whether case should be ignored.
     * @see SemanticsProperties.Text
     */
    fun assertTextContains(
        @StringRes value: Int,
        substring: Boolean = false,
        ignoreCase: Boolean = false
    ) {
        assertTextContains(getResourceString(value), substring, ignoreCase)
    }

    /**
     * Asserts the node's value equals the given value.
     *
     * For further details please check [SemanticsProperties.StateDescription].
     * Throws [AssertionError] if the node's value is not equal to `value`, or if the node has no value
     */
    fun assertValueEquals(@StringRes value: Int) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) { assertValueEquals(getResourceString(value)) }
    }
}
