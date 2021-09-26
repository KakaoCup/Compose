package io.github.kakaocup.compose.screens

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import io.github.kakaocup.compose.MainActivity
import io.github.kakaocup.compose.testframework.ComposeScreen
import io.github.kakaocup.compose.testframework.KNode

class MainActivityScreen(composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>): ComposeScreen<MainActivityScreen, MainActivity>(composeTestRule) {

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