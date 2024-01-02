package io.github.kakaocup.compose.test

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.sample.MainActivity
import io.github.kakaocup.compose.sample.R
import io.github.kakaocup.compose.screen.MainActivityScreen
import org.junit.Rule
import org.junit.Test

class SimpleTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun simpleTest() {
        onComposeScreen<MainActivityScreen>(composeTestRule) {
            myButton {
                assertIsDisplayed()
                assertTextContains("Button 1")
                assertTextContains(R.string.button_1)
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

    @Test
    fun iconTest() {
        onComposeScreen<MainActivityScreen>(composeTestRule) {
            changeIconsButton {
                assertIsDisplayed()
            }
            iconDrawableRes {
                assertIsDisplayed()
                assertContentEquals(R.drawable.ic_android)
                assertTintColorEquals(Color.Black)
                assertTintColorEquals("000000")
                assertTintColorEquals("#000000")
                assertTintColorEquals(0xFF000000)
            }

            iconImageVector {
                assertIsDisplayed()
                assertContentEquals(Icons.Filled.AccountCircle)
                assertTintColorEquals(Color.Black)
                assertTintColorEquals("000000")
                assertTintColorEquals("#000000")
                assertTintColorEquals(0xFF000000)
            }

            changeIconsButton.performClick()

            iconDrawableRes {
                assertIsDisplayed()
                assertContentEquals(R.drawable.ic_adb)
                assertTintColorEquals(Color.Blue)
                assertTintColorEquals("0000FF")
                assertTintColorEquals("#0000FF")
                assertTintColorEquals(0xFF0000FF)
            }

            iconImageVector {
                assertIsDisplayed()
                assertContentEquals(Icons.Filled.Call)
                assertTintColorEquals(Color.Blue)
                assertTintColorEquals("0000FF")
                assertTintColorEquals("#0000FF")
                assertTintColorEquals(0xFF0000FF)
            }
        }
    }
}
