package io.github.kakaocup.compose.test

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import io.github.kakaocup.compose.sample.MainScreen
import io.github.kakaocup.compose.node.element.ComposeScreen
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

        ComposeScreen.onComposeScreen<MainActivityScreen>(composeTestRule) {
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