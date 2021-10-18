package io.github.kakaocup.compose.node.matchers

import androidx.compose.ui.test.SemanticsMatcher

/**
 * Matcher that returns any node in the hierarchy.
 */
internal fun isAny() = SemanticsMatcher("isAny") { true }