package io.github.kakaocup.compose.utilities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher

internal object ColorAssertionsUtils {
    fun hasColor(expectedColor: Color, semanticsPropertyKey: SemanticsPropertyKey<*>): SemanticsMatcher = SemanticsMatcher(
        "The color is expected to be $expectedColor, but the actual color is different"
    ) { node ->
        val actualColor = node.config.getOrNull(semanticsPropertyKey)
            ?: error("Compose view does not contain $semanticsPropertyKey modifier")

        return@SemanticsMatcher actualColor == expectedColor
    }

    fun hasColor(expectedColor: String, semanticsPropertyKey: SemanticsPropertyKey<*>): SemanticsMatcher = SemanticsMatcher(
        "The color is expected to be $expectedColor, but the actual color is different"
    ) { node ->
        val actualColor = node.config.getOrNull(semanticsPropertyKey)
            ?: error("Compose view does not contain $semanticsPropertyKey modifier")

        return@SemanticsMatcher actualColor == getComposeColor(expectedColor)
    }

    fun hasColor(expectedColor: Long, semanticsPropertyKey: SemanticsPropertyKey<*>): SemanticsMatcher = SemanticsMatcher(
        "The color is expected to be $expectedColor, but the actual color is different"
    ) { node ->
        val actualColor = node.config.getOrNull(semanticsPropertyKey)
            ?: error("Compose view does not contain $semanticsPropertyKey modifier")

        return@SemanticsMatcher actualColor == Color(expectedColor)
    }

    fun getComposeColor(color: String): Color {
        val colorString = if (color.contains("#")) color else "#$color"
        return Color(android.graphics.Color.parseColor(colorString))
    }
}
