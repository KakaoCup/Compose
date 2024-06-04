package io.github.kakaocup.compose.node.builder

import androidx.compose.ui.test.SemanticsMatcher
import io.github.kakaocup.compose.KakaoCompose

data class NodeMatcher(
    val matcher: SemanticsMatcher,
    val position: Int = 0,
    val useUnmergedTree: Boolean = KakaoCompose.Override.useUnmergedTree ?: false,
)