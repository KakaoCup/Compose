package io.github.kakaocup.compose.node

import androidx.compose.ui.test.SemanticsMatcher

data class UserMatcher(
    val matcher: SemanticsMatcher,
    val position: Int = 0,
    val useUnmergedTree: Boolean = false,
)