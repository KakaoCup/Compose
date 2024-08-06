package io.github.kakaocup.compose.foundation

import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher

fun <T>hasProperty(expected: T, semanticsPropertyKey: SemanticsPropertyKey<*>, name: String): SemanticsMatcher = SemanticsMatcher(
    "The $name is expected to be $expected, but the actual $name is different"
) { node ->
    val actual = node.config.getOrNull(semanticsPropertyKey)
        ?: error("Compose view does not contain $semanticsPropertyKey modifier")

    return@SemanticsMatcher actual == expected
}
