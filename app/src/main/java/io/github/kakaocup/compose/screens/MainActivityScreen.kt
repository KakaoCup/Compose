package io.github.kakaocup.compose.screens

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import io.github.kakaocup.compose.MainActivity
import io.github.kakaocup.compose.testframework.ComposeScreen
import io.github.kakaocup.compose.testframework.KNode

class MainActivityScreen(composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>): ComposeScreen<MainActivityScreen, MainActivity>(composeTestRule) {

    val myButton = KNode(this) {
        withTag("mySimpleText")
    }
}