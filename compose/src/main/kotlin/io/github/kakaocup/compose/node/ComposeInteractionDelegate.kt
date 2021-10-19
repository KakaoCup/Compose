package io.github.kakaocup.compose.node

import androidx.compose.ui.test.SemanticsNodeInteraction

class ComposeInteractionDelegate(
    private val nodeProvider: () -> SemanticsNodeInteraction
) {

    val nodeInteraction: SemanticsNodeInteraction by lazy(LazyThreadSafetyMode.NONE) {
        nodeProvider.invoke()
    }
}