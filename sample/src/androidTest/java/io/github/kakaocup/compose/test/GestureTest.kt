package io.github.kakaocup.compose.test

import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.MainActivity
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.screen.MainActivityScreen
import org.junit.Rule
import org.junit.Test

class GestureTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun gestureTest() {
        onComposeScreen<MainActivityScreen>(composeTestRule) {
            clickCounter {
                assertTextEquals("0")
                performGesture {
                    click()
                }
                assertTextEquals("1")
            }
        }
    }
}
