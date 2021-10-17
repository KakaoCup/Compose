package io.github.kakaocup.compose.test

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import io.github.kakaocup.compose.MainScreen
import io.github.kakaocup.compose.node.extensions.onNode
import io.github.kakaocup.compose.screen.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.screen.MainActivityScreen
import org.junit.Rule
import org.junit.Test

class SimpleRuleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun simpleTest() {
        composeTestRule.setContent {
            MaterialTheme {
                MainScreen()
            }
        }

        onComposeScreen<MainActivityScreen>(composeTestRule) {
            myButton {
                assertIsDisplayed()
                assertTextContains("Button 1")
            }

            myText1 {
                assertIsDisplayed()
                assertTextContains("Simple text 1")
            }

            myText2 {
                assertIsDisplayed()
                assertTextContains("Simple text 2")
            }

            onNode {
                hasTestTag("doesNotExist")
            }.invoke {
                assertDoesNotExist()
            }
        }
    }
}