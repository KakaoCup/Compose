package io.github.kakaocup.compose.foundation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assert
import io.github.kakaocup.compose.node.assertion.NodeAssertions
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.node.core.BaseNode

open class KTextNode : BaseNode<KTextNode> {
    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
        nodeMatcher: NodeMatcher,
        parentNode: BaseNode<*>? = null,
    ) : super(semanticsProvider, nodeMatcher, parentNode)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
        viewBuilderAction: ViewBuilder.() -> Unit,
    ) : super(semanticsProvider, viewBuilderAction)

    constructor(
        semanticsProvider: SemanticsNodeInteractionsProvider? = null,
        nodeMatcher: NodeMatcher,
    ) : super(semanticsProvider, nodeMatcher)

    /**
     * Asserts that the text color contains the given [color].
     *
     * Throws [AssertionError] if the text color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [TextColorSemanticKey] modifier.
     */
    fun assertTextColorEquals(color: Color) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(hasProperty(color, TextColorSemanticKey, "text color"))
        }
    }

    /**
     * Asserts that text color contains the given [color].
     *
     * Throws [AssertionError] if the text color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [TextColorSemanticKey] modifier.
     * Throws [IllegalArgumentException] if the color value is incorrect.
     */
    fun assertTextColorEquals(color: String) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(
                hasProperty(
                    Color(android.graphics.Color.parseColor(color)),
                    TextColorSemanticKey,
                    "text color"
                )
            )
        }
    }

    /**
     * Asserts that the text color contains the given [color].
     *
     * Throws [AssertionError] if the text color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [TextColorSemanticKey] modifier.
     */
    fun assertTextColorEquals(color: Long) {
        delegate.check(NodeAssertions.ComposeBaseAssertionType.ASSERT_VALUE_EQUALS) {
            assert(hasProperty(Color(color), TextColorSemanticKey, "text color"))
        }
    }
}
