package io.github.kakaocup.compose

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import io.github.kakaocup.compose.node.KNode
import io.github.kakaocup.compose.screen.ComposeScreen

class MainActivityScreen(composeTestRule: AndroidComposeTestRule<*, *>) :
    ComposeScreen<MainActivityScreen>(composeTestRule) {

    val myText1 = KNode(this) {
        hasTestTag("mySimpleText")
        hasPosition(0)
    }

    val myText2 = KNode(this) {
        hasTestTag("mySimpleText")
        hasPosition(1)
    }

    val myButton = KNode(this) {
        hasTestTag("myTestButton")
        hasText("Button 1")
    }
}