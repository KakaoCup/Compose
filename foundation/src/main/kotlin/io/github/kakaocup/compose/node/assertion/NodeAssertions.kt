package io.github.kakaocup.compose.node.assertion

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.*
import io.github.kakaocup.compose.intercept.delegate.ComposeDelegate
import io.github.kakaocup.compose.intercept.operation.ComposeOperationType

interface NodeAssertions {
    val delegate: ComposeDelegate

    fun assertIsDisplayed() {
        delegate.check(ComposeBaseAssertionType.IS_DISPLAYED) { assertIsDisplayed() }
    }

    /**
     * Asserts that the current semantics node is not displayed on screen.
     *
     * Throws [AssertionError] if the node is displayed.
     */
    fun assertIsNotDisplayed() {
        delegate.check(ComposeBaseAssertionType.IS_NOT_DISPLAYED) { assertIsNotDisplayed() }
    }

    /**
     * Asserts that the current semantics node is enabled.
     *
     * Throws [AssertionError] if the node is not enabled or does not define the property at all.
     */
    fun assertIsEnabled() {
        delegate.check(ComposeBaseAssertionType.IS_ENABLED) { assertIsEnabled() }
    }

    /**
     * Asserts that the current semantics node is not enabled.
     *
     * Throws [AssertionError] if the node is enabled or does not defined the property at all.
     */
    fun assertIsNotEnabled() {
        delegate.check(ComposeBaseAssertionType.IS_NOT_ENABLED) { assertIsNotEnabled() }
    }

    /**
     * Asserts that the current semantics node is checked.
     *
     * Throws [AssertionError] if the node is not unchecked, indeterminate, or not toggleable.
     */
    fun assertIsOn() {
        delegate.check(ComposeBaseAssertionType.IS_ON) { assertIsOn() }
    }

    /**
     * Asserts that the current semantics node is unchecked.
     *
     * Throws [AssertionError] if the node is checked, indeterminate, or not toggleable.
     */
    fun assertIsOff() {
        delegate.check(ComposeBaseAssertionType.IS_OFF) { assertIsOff() }
    }

    /**
     * Asserts that the current semantics node is selected.
     *
     * Throws [AssertionError] if the node is unselected or not selectable.
     */
    fun assertIsSelected() {
        delegate.check(ComposeBaseAssertionType.IS_SELECTED) { assertIsSelected() }
    }

    /**
     * Asserts that the current semantics node is not selected.
     *
     * Throws [AssertionError] if the node is selected or not selectable.
     */
    fun assertIsNotSelected() {
        delegate.check(ComposeBaseAssertionType.IS_NOT_SELECTED) { assertIsNotSelected() }
    }
    /**
     * Asserts that the current semantics node is toggleable.
     *
     * Throws [AssertionError] if the node is not toggleable.
     */
    fun assertIsToggleable() {
        delegate.check(ComposeBaseAssertionType.IS_TOGGLEABLE) { assertIsToggleable() }
    }

    /**
     * Asserts that the current semantics node is selectable.
     *
     * Throws [AssertionError] if the node is not selectable.
     */
    fun assertIsSelectable() {
        delegate.check(ComposeBaseAssertionType.IS_SELECTABLE) { assertIsSelectable() }
    }
    /**
     * Asserts that the current semantics node has a focus.
     *
     * Throws [AssertionError] if the node is not in the focus or does not defined the property at all.
     */
    fun assertIsFocused() {
        delegate.check(ComposeBaseAssertionType.IS_FOCUSED) { assertIsFocused() }
    }

    /**
     * Asserts that the current semantics node does not have a focus.
     *
     * Throws [AssertionError] if the node is in the focus or does not defined the property at all.
     */
    fun assertIsNotFocused() {
        delegate.check(ComposeBaseAssertionType.IS_NOT_FOCUSED) { assertIsNotFocused() }
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
        delegate.check(ComposeBaseAssertionType.ASSERT_CONTENT_DESCRIPTION_EQUALS) { assertContentDescriptionEquals(values = values) }
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
        delegate.check(ComposeBaseAssertionType.ASSERT_CONTENT_DESCRIPTION_CONTAINS) { assertContentDescriptionContains(value, substring, ignoreCase) }
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
        delegate.check(ComposeBaseAssertionType.ASSERT_TEXT_EQUALS) { assertTextEquals(values = values, includeEditableText = includeEditableText) }
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
        delegate.check(ComposeBaseAssertionType.ASSERT_TEXT_CONTAINS) { assertTextContains(value, substring, ignoreCase) }
    }

    /**
     * Asserts the node's value equals the given value.
     *
     * For further details please check [SemanticsProperties.StateDescription].
     * Throws [AssertionError] if the node's value is not equal to `value`, or if the node has no value
     */
    fun assertValueEquals(value: String) {
        delegate.check(ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) { assertValueEquals(value) }
    }

    /**
     * Asserts the node's range info equals the given value.
     *
     * For further details please check [SemanticsProperties.ProgressBarRangeInfo].
     * Throws [AssertionError] if the node's value is not equal to `value`, or if the node has no value
     */
    fun assertRangeInfoEquals(value: ProgressBarRangeInfo) {
        delegate.check(ComposeBaseAssertionType.ASSERT_RANGE_INFO_EQUALS) { assertRangeInfoEquals(value) }
    }

    /**
     * Asserts that the current semantics node has a click action.
     *
     * Throws [AssertionError] if the node is doesn't have a click action.
     */
    fun assertHasClickAction() {
        delegate.check(ComposeBaseAssertionType.ASSERT_HAS_CLICK_ACTION) { assertHasClickAction() }
    }

    /**
     * Asserts that the current semantics node has doesn't have a click action.
     *
     * Throws [AssertionError] if the node has a click action.
     */
    fun assertHasNoClickAction() {
        delegate.check(ComposeBaseAssertionType.ASSERT_HAS_NO_CLICK_ACTION) { assertHasNoClickAction() }
    }

    /**
     * Asserts that no item was found or that the item is no longer in the hierarchy.
     *
     * This will synchronize with the UI and fetch all the nodes again to ensure it has latest data.
     *
     * @throws [AssertionError] if the assert fails.
     */
    fun assertDoesNotExist() {
        delegate.check(ComposeBaseAssertionType.ASSERT_DOES_NOT_EXIST) { assertDoesNotExist() }
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
    fun assertExists(errorMessageOnFail: String? = null) {
        delegate.check(ComposeBaseAssertionType.ASSERT_EXISTS) { assertExists(errorMessageOnFail) }
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
        delegate.check(ComposeBaseAssertionType.ASSERT) { assert(matcher, messagePrefixOnError) }
    }

    /**
     * Asserts that the provided `expected` is satisfied for this node.
     * @param expected expected value.
     * @param semanticsPropertyKey semantics Property key
     * @param property free text about the field (Displayed in case of [AssertionError])
     * @throws [AssertionError] if the matcher does not match or the node can no longer be found.
     */
    fun <T>assertHasProperty(expected: T,
                             semanticsPropertyKey: SemanticsPropertyKey<*>,
                             property: String? = null,
                             ) {
        delegate.check(ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(
                SemanticsMatcher(
                    property?.let { "The $it is expected to be $expected, but the actual $it is different" }.orEmpty()
                ) { node ->
                    val actual = node.config.getOrNull(semanticsPropertyKey)
                        ?: error("Compose view does not contain $semanticsPropertyKey modifier")

                    return@SemanticsMatcher actual == expected
                }
            )
        }
    }

    enum class ComposeBaseAssertionType : ComposeOperationType {
        IS_DISPLAYED,
        IS_NOT_DISPLAYED,
        IS_ENABLED,
        IS_NOT_ENABLED,
        IS_ON,
        IS_OFF,
        IS_SELECTED,
        IS_NOT_SELECTED,
        IS_TOGGLEABLE,
        IS_SELECTABLE,
        IS_FOCUSED,
        IS_NOT_FOCUSED,
        ASSERT_CONTENT_DESCRIPTION_EQUALS,
        ASSERT_CONTENT_DESCRIPTION_CONTAINS,
        ASSERT_TEXT_EQUALS,
        ASSERT_TEXT_CONTAINS,
        ASSERT_VALUE_EQUALS,
        ASSERT_RANGE_INFO_EQUALS,
        ASSERT_HAS_CLICK_ACTION,
        ASSERT_HAS_NO_CLICK_ACTION,
        ASSERT_DOES_NOT_EXIST,
        ASSERT_EXISTS,
        ASSERT,
    }
}