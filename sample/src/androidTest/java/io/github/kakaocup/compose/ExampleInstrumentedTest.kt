package io.github.kakaocup.compose

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.node.extensions.onNode
import io.github.kakaocup.compose.screen.ComposeScreen.Companion.onComposeScreen
import org.junit.Rule
import org.junit.Test

class ExampleInstrumentedTest {
    @Rule
    @JvmField
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun simpleTest() {
        composeTestRule.onNode(hasText("Simple text 1")).assertExists()

        onComposeScreen<MainActivityScreen>(composeTestRule) {
            myButton {
                assertIsDisplayed()
                assertTextContains("Button 1")
            }

            myText1 {
                assertIsDisplayed()
                assertTextContains("Simple text 1")
            }

            onNode {
                hasTestTag("doesNotExist")
            }.invoke {
                assertDoesNotExist()
            }
        }
    }

    @Test
    fun unmergedTreeTest() {
        onComposeScreen<MainActivityScreen>(composeTestRule) {
            myOutlinedButtonText {
                assertDoesNotExist()
            }

            unmergedTreeOutlinedButtonText {
                assertExists()
            }
        }
    }

}