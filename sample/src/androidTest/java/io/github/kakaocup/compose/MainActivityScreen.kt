package io.github.kakaocup.compose

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import io.github.kakaocup.compose.node.KNode
import io.github.kakaocup.compose.screen.ComposeScreen

class MainActivityScreen(composeTestRule: AndroidComposeTestRule<*, *>) :
    ComposeScreen<MainActivityScreen>(composeTestRule) {

    val myText1 = KNode(this) {
        hasTestTag("mySimpleText")
    }

    val myButton = KNode(this) {
        hasTestTag("myTestButton")
        hasText("Button 1")
    }

    val myOutlinedButtonText = KNode(this) {
        hasTestTag("myTestOutlinedButtonContent")
    }

    val unmergedTreeOutlinedButtonText = KNode(this) {
        useUnmergedTree = true
        hasTestTag("myTestOutlinedButtonContent")
    }
}
