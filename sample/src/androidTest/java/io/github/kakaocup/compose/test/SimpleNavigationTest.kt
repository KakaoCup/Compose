package io.github.kakaocup.compose.test

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import io.github.kakaocup.compose.SimpleNavigationScreen
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.screen.navigation.FirstNavigationScreen
import io.github.kakaocup.compose.screen.navigation.SecondNavigationScreen
import org.junit.Rule
import org.junit.Test

class SimpleNavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun simpleNavigationTest() {
        composeTestRule.setContent {
            MaterialTheme {
                SimpleNavigationScreen()
            }
        }

        onComposeScreen<FirstNavigationScreen>(composeTestRule) {
            sharedWidget.title {
                assertIsDisplayed()
                assertTextEquals("First screen")
            }
            navigationButton.performClick()
        }

        onComposeScreen<SecondNavigationScreen>(composeTestRule) {
            sharedWidget.title {
                assertIsDisplayed()
                assertTextEquals("Second screen")
            }
        }
    }
}