package io.github.kakaocup.compose.test

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import io.github.kakaocup.compose.sample.MainScreen
import io.github.kakaocup.compose.screen.MainActivityScreen
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class SimpleTest {

    @Test
    fun simpleTest() = runComposeUiTest {
        setContent { MainScreen() }

        MainActivityScreen(this).apply {
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
