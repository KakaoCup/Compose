package io.github.kakaocup.compose.node.builder

import androidx.compose.ui.test.SemanticsMatcher

data class NodeMatcher(
    val matcher: SemanticsMatcher,
    val position: Int = 0,
    val useUnmergedTree: Boolean = false,
)