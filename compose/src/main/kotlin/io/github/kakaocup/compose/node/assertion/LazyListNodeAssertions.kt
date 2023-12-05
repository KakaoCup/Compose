package io.github.kakaocup.compose.node.assertion

import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert

interface LazyListNodeAssertions : NodeAssertions {
    val lengthSemanticsPropertyKey: SemanticsPropertyKey<Int>

    /**
     * Asserts that the lazy list length contains the given [length].
     *
     * Throws [AssertionError] if the lazy list's length value is not equal to `length`.
     * Throws [IllegalStateException] if the lazy list does not contain the [lengthSemanticsPropertyKey] modifier.
     */
    fun assertLengthEquals(length: Int) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) { assert(hasLazyListLength(length)) }
    }

    private fun hasLazyListLength(length: Int): SemanticsMatcher = SemanticsMatcher(
        "The length of the lazy list is expected to be ${length}, but the actual size is different"
    ) { node ->
        val actualLength = node.config.getOrNull(lengthSemanticsPropertyKey) ?: error("Lazy list does not contain $lengthSemanticsPropertyKey modifier")
        return@SemanticsMatcher actualLength == length
    }
}
