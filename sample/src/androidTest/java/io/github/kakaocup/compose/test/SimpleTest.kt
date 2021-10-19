package io.github.kakaocup.compose.test

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.MainActivity
import io.github.kakaocup.compose.extensions.screens
import org.junit.Rule
import org.junit.Test

class SimpleTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun simpleTest() {
        composeTestRule.screens.main {
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