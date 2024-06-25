package io.github.kakaocup.compose.node.builder

import androidx.annotation.StringRes
import androidx.compose.ui.semantics.*
import androidx.compose.ui.test.*
import androidx.compose.ui.text.input.ImeAction
import io.github.kakaocup.compose.KakaoCompose
import io.github.kakaocup.compose.node.core.ComposeMarker
import io.github.kakaocup.compose.utilities.getResourceString

@ComposeMarker
class ViewBuilder {

    private val semanticsMatcherList = mutableListOf<SemanticsMatcher>()

    private var position = 0

    var useUnmergedTree: Boolean = KakaoCompose.Override.useUnmergedTree ?: false

    fun isEnabled() = addFilter(androidx.compose.ui.test.isEnabled())

    /**
     * Returns whether the node is not enabled.
     *
     * @see SemanticsProperties.Disabled
     */
    fun isNotEnabled() = addFilter(androidx.compose.ui.test.isNotEnabled())

    /**
     * Return whether the node is checkable.
     *
     * @see SemanticsProperties.ToggleableState
     */
    fun isToggleable() = addFilter(androidx.compose.ui.test.isToggleable())

    /**
     * Returns whether the node is toggled.
     *
     * @see SemanticsProperties.ToggleableState
     */
    fun isOn() = addFilter(androidx.compose.ui.test.isOn())
    /**
     * Returns whether the node is not toggled.
     *
     * @see SemanticsProperties.ToggleableState
     */
    fun isOff() = addFilter(androidx.compose.ui.test.isOff())

    /**
     * Return whether the node is selectable.
     *
     * @see SemanticsProperties.Selected
     */
    fun isSelectable() = addFilter(androidx.compose.ui.test.isSelectable())

    /**
     * Returns whether the node is selected.
     *
     * @see SemanticsProperties.Selected
     */
    fun isSelected() = addFilter(androidx.compose.ui.test.isSelected())

    /**
     * Returns whether the node is not selected.
     *
     * @see SemanticsProperties.Selected
     */
    fun isNotSelected() = addFilter(androidx.compose.ui.test.isNotSelected())

    /**
     * Return whether the node is able to receive focus
     *
     * @see SemanticsProperties.Focused
     */
    fun isFocusable() = addFilter(androidx.compose.ui.test.isFocusable())

    /**
     * Return whether the node is not able to receive focus.
     *
     * @see SemanticsProperties.Focused
     */
    fun isNotFocusable() = addFilter(androidx.compose.ui.test.isNotFocusable())

    /**
     * Returns whether the node is focused.
     *
     * @see SemanticsProperties.Focused
     */
    fun isFocused() = addFilter(androidx.compose.ui.test.isFocused())

    /**
     * Returns whether the node is not focused.
     *
     * @see SemanticsProperties.Focused
     */
    fun isNotFocused() = addFilter(androidx.compose.ui.test.isNotFocused())

    /**
     * Return whether the node has a semantics click action defined.
     *
     * @see SemanticsActions.OnClick
     */
    fun hasClickAction() = addFilter(androidx.compose.ui.test.hasClickAction())
    /**
     * Return whether the node has no semantics click action defined.
     *
     * @see SemanticsActions.OnClick
     */
    fun hasNoClickAction() = addFilter(androidx.compose.ui.test.hasNoClickAction())

    /**
     * Return whether the node has a semantics scrollable action defined.
     *
     * @see SemanticsActions.ScrollBy
     */
    fun hasScrollAction() = addFilter(androidx.compose.ui.test.hasScrollAction())

    /**
     * Return whether the node has no semantics scrollable action defined.
     *
     * @see SemanticsActions.ScrollBy
     */
    fun hasNoScrollAction() = addFilter(androidx.compose.ui.test.hasNoScrollAction())

    /**
     * Returns whether the node's content description contains the given [value].
     *
     * Note that in merged semantics tree there can be a list of content descriptions that got merged
     * from the child nodes. Typically an accessibility tooling will decide based on its heuristics
     * which ones to announce.
     *
     * @param value Value to match as one of the items in the list of content descriptions.
     * @param substring Whether to use substring matching.
     * @param ignoreCase Whether case should be ignored.
     *
     * @see SemanticsProperties.ContentDescription
     */
    fun hasContentDescription(
        value: String,
        substring: Boolean = false,
        ignoreCase: Boolean = false
    ) = addFilter(androidx.compose.ui.test.hasContentDescription(value, substring, ignoreCase))

    /**
     * Returns whether the node's content description contains the given [value].
     *
     * Note that in merged semantics tree there can be a list of content descriptions that got merged
     * from the child nodes. Typically an accessibility tooling will decide based on its heuristics
     * which ones to announce.
     *
     * @param resId String resource id to match as one of the items in the list of content descriptions.
     * @param substring Whether to use substring matching.
     * @param ignoreCase Whether case should be ignored.
     *
     * @see SemanticsProperties.ContentDescription
     */
    fun hasContentDescription(
        @StringRes resId: Int,
        substring: Boolean = false,
        ignoreCase: Boolean = false
    ) = hasContentDescription(getResourceString(resId), substring, ignoreCase)

    /**
     * Returns whether the node's content description contains exactly the given [values] and nothing
     * else.
     *
     * Note that in merged semantics tree there can be a list of content descriptions that got merged
     * from the child nodes. Typically an accessibility tooling will decide based on its heuristics
     * which ones to announce.
     *
     * @param values List of values to match (the order does not matter)
     *
     * @see SemanticsProperties.ContentDescription
     */
    fun hasContentDescriptionExactly(
        vararg values: String
    ) = addFilter(androidx.compose.ui.test.hasContentDescriptionExactly(values = values))

    /**
     * Returns whether the node's content description contains exactly the given [values] and nothing
     * else.
     *
     * Note that in merged semantics tree there can be a list of content descriptions that got merged
     * from the child nodes. Typically an accessibility tooling will decide based on its heuristics
     * which ones to announce.
     *
     * @param resIds List of string resources id's values to match (the order does not matter)
     *
     * @see SemanticsProperties.ContentDescription
     */
    fun hasContentDescriptionExactly(
        @StringRes vararg resIds: Int
    ) = hasContentDescriptionExactly(values = resIds.map(::getResourceString).toTypedArray())

    /**
     * Returns whether the node's text contains the given [text].
     *
     * This will also search in [SemanticsProperties.EditableText].
     *
     * Note that in merged semantics tree there can be a list of text items that got merged from
     * the child nodes. Typically an accessibility tooling will decide based on its heuristics which
     * ones to use.
     *
     * @param text Value to match as one of the items in the list of text values.
     * @param substring Whether to use substring matching.
     * @param ignoreCase Whether case should be ignored.
     *
     * @see SemanticsProperties.Text
     * @see SemanticsProperties.EditableText
     */
    fun hasText(
        text: String,
        substring: Boolean = false,
        ignoreCase: Boolean = false
    ) = addFilter(androidx.compose.ui.test.hasText(text, substring, ignoreCase))

    /**
     * Returns whether the node's text contains the given [text].
     *
     * This will also search in [SemanticsProperties.EditableText].
     *
     * Note that in merged semantics tree there can be a list of text items that got merged from
     * the child nodes. Typically an accessibility tooling will decide based on its heuristics which
     * ones to use.
     *
     * @param resId String resource id value to match as one of the items in the list of text values.
     * @param substring Whether to use substring matching.
     * @param ignoreCase Whether case should be ignored.
     *
     * @see SemanticsProperties.Text
     * @see SemanticsProperties.EditableText
     */
    fun hasText(
        @StringRes resId: Int,
        substring: Boolean = false,
        ignoreCase: Boolean = false
    ) = hasText(getResourceString(resId), substring, ignoreCase)

    /**
     * Returns whether the node's text contains exactly the given [values] and nothing else.
     *
     * This will also search in [SemanticsProperties.EditableText] by default.
     *
     * Note that in merged semantics tree there can be a list of text items that got merged from
     * the child nodes. Typically an accessibility tooling will decide based on its heuristics which
     * ones to use.
     *
     * @param textValues List of values to match (the order does not matter)
     * @param includeEditableText Whether to also assert against the editable text.
     *
     * @see SemanticsProperties.Text
     * @see SemanticsProperties.EditableText
     */
    fun hasTextExactly(
        vararg textValues: String,
        includeEditableText: Boolean = true
    ) = addFilter(androidx.compose.ui.test.hasTextExactly(
        textValues = textValues,
        includeEditableText = includeEditableText))

    /**
     * Returns whether the node's text contains exactly the given [values] and nothing else.
     *
     * This will also search in [SemanticsProperties.EditableText] by default.
     *
     * Note that in merged semantics tree there can be a list of text items that got merged from
     * the child nodes. Typically an accessibility tooling will decide based on its heuristics which
     * ones to use.
     *
     * @param resIds Values List of string resources id's to match (the order does not matter)
     * @param includeEditableText Whether to also assert against the editable text.
     *
     * @see SemanticsProperties.Text
     * @see SemanticsProperties.EditableText
     */
    fun hasTextExactly(
        @StringRes vararg resIds: Int,
        includeEditableText: Boolean = true
    ) = hasTextExactly(
        textValues = resIds.map(::getResourceString).toTypedArray(),
        includeEditableText = includeEditableText
    )

    /**
     * Returns whether the node's value matches exactly to the given accessibility value.
     *
     * @param value Value to match.
     *
     * @see SemanticsProperties.StateDescription
     */
    fun hasStateDescription(value: String) =
        addFilter(androidx.compose.ui.test.hasStateDescription(value))

    /**
     * Returns whether the node's value matches exactly to the given accessibility value.
     *
     * @param resId String resource id value to match.
     *
     * @see SemanticsProperties.StateDescription
     */
    fun hasStateDescription(@StringRes resId: Int) = hasStateDescription(getResourceString(resId))

    /**
     * Returns whether the node is marked as an accessibility header.
     *
     * @see SemanticsProperties.Heading
     */
    fun isHeading() = addFilter(androidx.compose.ui.test.isHeading())

    /**
     * Returns whether the node's range info matches exactly to the given accessibility range info.
     *
     * @param rangeInfo range info to match.
     *
     * @see SemanticsProperties.ProgressBarRangeInfo
     */
    fun hasProgressBarRangeInfo(rangeInfo: ProgressBarRangeInfo) =
        addFilter(androidx.compose.ui.test.hasProgressBarRangeInfo(rangeInfo))

    /**
     * Returns whether the node is annotated by the given test tag.
     *
     * @param testTag Value to match.
     *
     * @see SemanticsProperties.TestTag
     */
    fun hasTestTag(testTag: String) = addFilter(androidx.compose.ui.test.hasTestTag(testTag))

    /**
     * Returns whether the node is annotated by the given test tag.
     *
     * @param resId String resource id to match.
     *
     * @see SemanticsProperties.TestTag
     */
    fun hasTestTag(@StringRes resId: Int) = hasTestTag(getResourceString(resId))

    /**
     * Returns whether the node is a dialog.
     *
     * This only checks if the node itself is a dialog, not if it is _part of_ a dialog. Use
     * `hasAnyAncestorThat(isDialog())` for that.
     *
     * @see SemanticsProperties.IsDialog
     */
    fun isDialog() = addFilter(androidx.compose.ui.test.isDialog())

    /**
     * Returns whether the node is a popup.
     *
     * This only checks if the node itself is a popup, not if it is _part of_ a popup. Use
     * `hasAnyAncestorThat(isPopup())` for that.
     *
     * @see SemanticsProperties.IsPopup
     */
    fun isPopup() = addFilter(androidx.compose.ui.test.isPopup())

    /**
     * Returns whether the node defines the given IME action.
     *
     * @param actionType the action to match.
     */
    fun hasImeAction(actionType: ImeAction) = addFilter(androidx.compose.ui.test.hasImeAction(actionType))

    /**
     * Returns whether the node defines semantics action to set text to it.
     *
     * This can be used to for instance filter out text fields.
     *
     * @see SemanticsActions.SetText
     */
    fun hasSetTextAction() = addFilter(androidx.compose.ui.test.hasSetTextAction())

    /**
     * Returns whether the node defines the ability to scroll to an item index.
     *
     * Note that not all scrollable containers have item indices. For example, a
     * [scrollable][androidx.compose.foundation.gestures.scrollable] doesn't have items with an
     * index, while [LazyColumn][androidx.compose.foundation.lazy.LazyColumn] does.
     */
    @ExperimentalTestApi
    fun hasScrollToIndexAction() = addFilter(androidx.compose.ui.test.hasScrollToIndexAction())

    /**
     * Returns whether the node defines the ability to scroll to an item identified by a key, such as
     * [LazyColumn][androidx.compose.foundation.lazy.LazyColumn] or
     * [LazyRow][androidx.compose.foundation.lazy.LazyRow].
     */
    @ExperimentalTestApi
    fun hasScrollToKeyAction() = addFilter(androidx.compose.ui.test.hasScrollToKeyAction())


    /**
     * Return whether the node is the root semantics node.
     *
     * There is always one root in every node tree, added implicitly by Compose.
     */
    fun isRoot() = addFilter(androidx.compose.ui.test.isRoot())

    /**
     * Returns whether the node's parent satisfies the given matcher.
     *
     * Returns false if no parent exists.
     */
    fun hasParent(matcher: SemanticsMatcher) = addFilter(androidx.compose.ui.test.hasParent(matcher))

    /**
     * Returns whether the node has at least one child that satisfies the given matcher.
     */
    fun hasAnyChild(matcher: SemanticsMatcher) = addFilter(androidx.compose.ui.test.hasAnyChild(matcher))

    /**
     * Returns whether the node has at least one sibling that satisfies the given matcher.
     *
     * Sibling is defined as a any other node that shares the same parent.
     */
    fun hasAnySibling(matcher: SemanticsMatcher) = addFilter(androidx.compose.ui.test.hasAnySibling(matcher))

    /**
     * Returns whether the node has at least one ancestor that satisfies the given matcher.
     *
     * Example: For the following tree
     * |-X
     * |-A
     *   |-B
     *     |-C1
     *     |-C2
     * In case of C1, we would check the matcher against A and B
     */
    fun hasAnyAncestor(matcher: SemanticsMatcher) = addFilter(androidx.compose.ui.test.hasAnyAncestor(matcher))

    /**
     * Returns whether the node has at least one descendant that satisfies the given matcher.
     *
     * Example: For the following tree
     * |-X
     * |-A
     *   |-B
     *     |-C1
     *     |-C2
     * In case of A, we would check the matcher against B,C1 and C2
     */
    fun hasAnyDescendant(matcher: SemanticsMatcher) = addFilter(androidx.compose.ui.test.hasAnyDescendant(matcher))

    /**
     * Returns whether the node matches exactly to the given custom matcher.
     */
    fun addSemanticsMatcher(matcher: SemanticsMatcher) = addFilter(matcher)

    fun hasPosition(position: Int) {
        this.position = position
    }

    private fun addFilter(semanticsMatcher: SemanticsMatcher) {
        semanticsMatcherList.add(semanticsMatcher)
    }

    fun build(): NodeMatcher {
        if (semanticsMatcherList.isEmpty()) throw ViewBuilderException("Please set matchers for your Element!")
        val matcher = semanticsMatcherList.reduce { finalMatcher, matcher -> finalMatcher and matcher }
        return NodeMatcher(matcher, position, useUnmergedTree)
    }
}

private class ViewBuilderException(message: String) : RuntimeException(message)
