package io.github.kakaocup.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import io.github.kakaocup.compose.screens.MainActivityScreen
import io.github.kakaocup.compose.testframework.ComposeScreen.Companion.onComposeScreen

import org.junit.Test
import org.junit.Rule

class ExampleInstrumentedTest {
    @Rule
    @JvmField
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testGreeting() {
        //Espresso style
        composeTestRule.onNodeWithText("Button 1").assertIsDisplayed()

        //Kakao Compose style
        onComposeScreen<MainActivityScreen>(composeTestRule) {
            myButton {
                isDisplayed()
            }
        }
    }
}