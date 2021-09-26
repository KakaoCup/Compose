package io.github.kakaocup.compose

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.screens.MainActivityScreen
import io.github.kakaocup.compose.testframework.ComposeScreen.Companion.onComposeScreen

import org.junit.Test
import org.junit.Rule

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
        }
    }
}