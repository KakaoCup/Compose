package io.github.kakaocup.compose.node.assertion

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert

interface ImageContentAssertions : NodeAssertions {
    val imageContentSemanticsPropertyKey: SemanticsPropertyKey<Any>

    /**
     * Asserts that the image or icon content contains the given [imageVector].
     *
     * Throws [AssertionError] if the image or icon content value is not equal to `imageVector`.
     * Throws [IllegalStateException] if the image or icon does not contain the [imageContentSemanticsPropertyKey] modifier.
     */
    fun assertContentEquals(imageVector: ImageVector) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) { assert(hasImageContent(imageVector)) }
    }

    /**
     * Asserts that the image or icon content contains the given [drawableRes].
     *
     * Throws [AssertionError] if the image or icon content value is not equal to `drawableRes`.
     * Throws [IllegalStateException] if the image or icon does not contain the [imageContentSemanticsPropertyKey] modifier.
     */
    fun assertContentEquals(@DrawableRes drawableRes: Int) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) { assert(hasImageContent(drawableRes)) }
    }

    private fun hasImageContent(expectedContent: Any): SemanticsMatcher = SemanticsMatcher(
        "The content is expected to be $expectedContent, but the actual content is different"
    ) { node ->
        val actual = node.config.getOrNull(imageContentSemanticsPropertyKey)
            ?: error("Compose view does not contain $imageContentSemanticsPropertyKey modifier")

        return@SemanticsMatcher actual == expectedContent
    }
}
