package io.github.kakaocup.compose.foundation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.builder.NodeMatcher
import io.github.kakaocup.compose.node.builder.ViewBuilder
import io.github.kakaocup.compose.node.core.BaseNode
import io.github.kakaocup.compose.semantics.IconImageContentSemanticKey
import io.github.kakaocup.compose.semantics.IconTintColorSemanticKey

open class KIconNode : BaseNode<KIconNode> {
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
     * Asserts that the compose view tint color contains the given [color].
     *
     * Throws [AssertionError] if the compose view tint color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [TintColorSemanticKey] modifier.
     */
    fun assertTintColorEquals(color: Color) {
        assertHasProperty(color, IconTintColorSemanticKey, "tint color")
    }

    /**
     * Asserts that the compose view tint color contains the given [color].
     *
     * Throws [AssertionError] if the compose view tint color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [TintColorSemanticKey] modifier.
     * Throws [IllegalArgumentException] if the color value is incorrect.
     */
    fun assertTintColorEquals(color: String) {
        assertHasProperty(
            Color(android.graphics.Color.parseColor(color)),
            IconTintColorSemanticKey,
            "tint color",
        )
    }

    /**
     * Asserts that the compose view tint color contains the given [color].
     *
     * Throws [AssertionError] if the compose view tint color value is not equal to `color`.
     * Throws [IllegalStateException] if the compose view does not contain the [TintColorSemanticKey] modifier.
     */
    fun assertTintColorEquals(color: Long) {
        assertHasProperty(
            Color(color),
            IconTintColorSemanticKey,
            "tint color",
        )
    }

    /**
     * Asserts that the image or icon content contains the given [imageVector].
     *
     * Throws [AssertionError] if the image or icon content value is not equal to `imageVector`.
     * Throws [IllegalStateException] if the image or icon does not contain the [ImageContentSemanticKey] modifier.
     */
    fun assertContentEquals(imageVector: ImageVector) {
        assertHasProperty(
            imageVector,
            IconImageContentSemanticKey,
            "image vector",
        )
    }

    /**
     * Asserts that the image or icon content contains the given [drawableRes].
     *
     * Throws [AssertionError] if the image or icon content value is not equal to `drawableRes`.
     * Throws [IllegalStateException] if the image or icon does not contain the [ImageContentSemanticKey] modifier.
     */
    fun assertContentEquals(@DrawableRes drawableRes: Int) {
        assertHasProperty(
            drawableRes,
            IconImageContentSemanticKey,
            "image drawable",
        )
    }
}
