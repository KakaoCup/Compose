package io.github.kakaocup.compose.node.assertion
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert

/**
 * Basic assertions for working with lists.
 */
interface ListNodeAssertions : NodeAssertions {

    /**
     * Semantic property for the list length.
     */
    val lengthSemanticsPropertyKey: SemanticsPropertyKey<Int>

    /**
     * Checks that the length of the list is equal to [length].
     *
     * @throws [AssertionError] if the list length is not equal to [length].
     * @throws [IllegalStateException] if the list does not provide the [lengthSemanticsPropertyKey] property.
     */
    fun assertLengthEquals(length: Int) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(hasListLength(length))
        }
    }

    private fun hasListLength(length: Int): SemanticsMatcher {
        return SemanticsMatcher(
            "The length of the list is expected to be $length, but the actual size is different"
        ) { node ->
            val actualLength = node.config.getOrNull(lengthSemanticsPropertyKey)
                ?: error("List does not contain $lengthSemanticsPropertyKey modifier")

            actualLength == length
        }
    }
}