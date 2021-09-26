package io.github.kakaocup.compose

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
        onComposeScreen<MainActivityScreen>(composeTestRule) {
            myButton {
                isDisplayed()
                textContains("Button 1")
            }

            myText1 {
                isDisplayed()
                textContains("Simple text 1")
            }

            myText2 {
                isDisplayed()
                textContains("Simple text 2")
            }

            onNode {
                hasTestTag("doesNotExist")
            }.invoke {
                doesNotExist()
            }
        }
    }
}