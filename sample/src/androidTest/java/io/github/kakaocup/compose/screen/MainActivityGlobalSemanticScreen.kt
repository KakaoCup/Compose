package io.github.kakaocup.compose.screen

import io.github.kakaocup.compose.node.KAppIconNode
import io.github.kakaocup.compose.node.KAppTextNode
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class MainActivityGlobalSemanticScreen :
    ComposeScreen<MainActivityGlobalSemanticScreen>(
        viewBuilderAction = { hasTestTag("MainScreen") }
) {

    val myText1: KNode = child {
        hasTestTag("mySimpleText")
        hasPosition(0)
    }
}
