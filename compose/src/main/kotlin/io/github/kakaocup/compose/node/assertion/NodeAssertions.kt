package io.github.kakaocup.compose.node.assertion

import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import io.github.kakaocup.compose.intercept.delegate.ComposeDelegate

interface NodeAssertions {
    val delegate: ComposeDelegate

    fun assertIsDisplayed() {
        delegate.check { it.assertIsDisplayed() }
    }

    /**
     * Asserts that the current semantics node is not displayed on screen.
     *
     * Throws [AssertionError] if the node is displayed.
     */
    fun assertIsNotDisplayed() {
        delegate.check { it.assertIsNotDisplayed() }
    }

    /**
     * Asserts that the current semantics node is enabled.
     *
     * Throws [AssertionError] if the node is not enabled or does not define the property at all.
     */
    fun assertIsEnabled() {
        delegate.check { it.assertIsEnabled() }
    }

    /**
     * Asserts that the current semantics node is not enabled.
     *
     * Throws [AssertionError] if the node is enabled or does not defined the property at all.
     */
    fun assertIsNotEnabled() {
        delegate.check { it.assertIsNotEnabled() }
    }

    /**
     * Asserts that the current semantics node is checked.
     *
     * Throws [AssertionError] if the node is not unchecked, indeterminate, or not toggleable.
     */
    fun assertIsOn() {
        delegate.check { it.assertIsOn() }
    }

    /**
     * Asserts that the current semantics node is unchecked.
     *
     * Throws [AssertionError] if the node is checked, indeterminate, or not toggleable.
     */
    fun assertIsOff() {
        delegate.check { it.assertIsOff() }
    }

    /**
     * Asserts that the current semantics node is selected.
     *
     * Throws [AssertionError] if the node is unselected or not selectable.
     */
    fun assertIsSelected() {
        delegate.check { it.assertIsSelected() }
    }

    /**
     * Asserts that the current semantics node is not selected.
     *
     * Throws [AssertionError] if the node is selected or not selectable.
     */
    fun assertIsNotSelected() {
        delegate.check { it.assertIsNotSelected() }
    }
    /**
     * Asserts that the current semantics node is toggleable.
     *
     * Throws [AssertionError] if the node is not toggleable.
     */
    fun assertIsToggleable() {
        delegate.check { it.assertIsToggleable() }
    }

    /**
     * Asserts that the current semantics node is selectable.
     *
     * Throws [AssertionError] if the node is not selectable.
     */
    fun assertIsSelectable() {
        delegate.check { it.assertIsSelectable() }
    }
    /**
     * Asserts that the current semantics node has a focus.
     *
     * Throws [AssertionError] if the node is not in the focus or does not defined the property at all.
     */
    fun assertIsFocused() {
        delegate.check { it.assertIsFocused() }
    }

    /**
     * Asserts that the current semantics node does not have a focus.
     *
     * Throws [AssertionError] if the node is in the focus or does not defined the property at all.
     */
    fun assertIsNotFocused() {
        delegate.check { it.assertIsNotFocused() }
    }

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
     * @param values List of values to match (the order does not matter)
     * @see SemanticsProperties.ContentDescription
     */
    fun assertContentDescriptionEquals(
        vararg values: String
    ) {
        delegate.check { it.assertContentDescriptionEquals(values = values) }
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
     * @param value Value to match as one of the items in the list of content descriptions.
     * @param substring Whether this can be satisfied as a substring match of an item in the list of
     * descriptions.
     * @param ignoreCase Whether case should be ignored.
     * @see SemanticsProperties.ContentDescription
     */
    fun assertContentDescriptionContains(
        value: String,
        substring: Boolean = false,
        ignoreCase: Boolean = false
    ) {
        delegate.check { it.assertContentDescriptionContains(value, substring, ignoreCase) }
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
     * @param values List of values to match (the order does not matter)
     * @param includeEditableText Whether to also assert against the editable text.
     * @see SemanticsProperties.ContentDescription
     */
    fun assertTextEquals(
        vararg values: String,
        includeEditableText: Boolean = true
    ) {
        delegate.check { it.assertTextEquals(values = values, includeEditableText = includeEditableText) }
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
     * @param value Value to match as one of the items in the list of text values.
     * @param substring Whether this can be satisfied as a substring match of an item in the list of
     * text.
     * @param ignoreCase Whether case should be ignored.
     * @see SemanticsProperties.Text
     */
    fun assertTextContains(
        value: String,
        substring: Boolean = false,
        ignoreCase: Boolean = false
    ) {
        delegate.check { it.assertTextContains(value, substring, ignoreCase) }
    }

    /**
     * Asserts the node's value equals the given value.
     *
     * For further details please check [SemanticsProperties.StateDescription].
     * Throws [AssertionError] if the node's value is not equal to `value`, or if the node has no value
     */
    fun assertValueEquals(value: String) {
        delegate.check { it.assertValueEquals(value) }
    }

    /**
     * Asserts the node's range info equals the given value.
     *
     * For further details please check [SemanticsProperties.ProgressBarRangeInfo].
     * Throws [AssertionError] if the node's value is not equal to `value`, or if the node has no value
     */
    fun assertRangeInfoEquals(value: ProgressBarRangeInfo) {
        delegate.check { it.assertRangeInfoEquals(value) }
    }

    /**
     * Asserts that the current semantics node has a click action.
     *
     * Throws [AssertionError] if the node is doesn't have a click action.
     */
    fun assertHasClickAction() {
        delegate.check { it.assertHasClickAction() }
    }

    /**
     * Asserts that the current semantics node has doesn't have a click action.
     *
     * Throws [AssertionError] if the node has a click action.
     */
    fun assertHasNoClickAction() {
        delegate.check { it.assertHasNoClickAction() }
    }

    /**
     * Asserts that no item was found or that the item is no longer in the hierarchy.
     *
     * This will synchronize with the UI and fetch all the nodes again to ensure it has latest data.
     *
     * @throws [AssertionError] if the assert fails.
     */
    fun assertDoesNotExist() {
        delegate.check { it.assertDoesNotExist() }
    }

    /**
     * Asserts that the component was found and is part of the component tree.
     *
     * This will synchronize with the UI and fetch all the nodes again to ensure it has latest data.
     * If you are using [fetchSemanticsNode] you don't need to call this. In fact you would just
     * introduce additional overhead.
     *
     * @param errorMessageOnFail Error message prefix to be added to the message in case this
     * asserts fails. This is typically used by operations that rely on this assert. Example prefix
     * could be: "Failed to perform doOnClick.".
     *
     * @throws [AssertionError] if the assert fails.
     */
    fun assertExists() {
        delegate.check { it.assertExists() }
    }

    /**
     * Asserts that the provided matcher is satisfied for this node.
     * @param matcher Matcher to verify.
     * @param messagePrefixOnError Prefix to be put in front of an error that gets thrown in case this assert fails.
     * This can be helpful in situations where this assert fails as part of a bigger operation that used this assert as a precondition check.
     * @throws [AssertionError] if the matcher does not match or the node can no longer be found.
     */
    fun assert(
        matcher: SemanticsMatcher,
        messagePrefixOnError: (() -> String)? = null
    ) {
        delegate.check { it.assert(matcher, messagePrefixOnError) }
    }
}