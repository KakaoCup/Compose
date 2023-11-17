package io.github.kakaocup.compose.test

import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.sample.MainActivity
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.screen.MainActivityScreen
import org.junit.Rule
import org.junit.Test

class TouchInputTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun touchInputTest() {
        onComposeScreen<MainActivityScreen>(composeTestRule) {
            clickCounter {
                assertTextEquals("0")
                performTouchInput {
                    click()
                }
                assertTextEquals("1")
            }
        }
    }
}
