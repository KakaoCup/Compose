package io.github.kakaocup.compose.test

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import io.github.kakaocup.compose.sample.MainActivity
import io.github.kakaocup.compose.screen.GlobalSemanticScreen
import org.junit.Rule
import org.junit.Test

class SimpleTestGlobalSemantic {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true
    )

    @Test
    fun simpleTest() {
        onComposeScreen<GlobalSemanticScreen> {
            myText1 {
                assertIsDisplayed()
                assertTextContains("Simple text 1")
            }
        }
    }
}
