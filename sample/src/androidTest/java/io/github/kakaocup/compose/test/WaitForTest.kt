package io.github.kakaocup.compose.test

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import io.github.kakaocup.compose.sample.DelayDisplayActivity
import io.github.kakaocup.compose.screen.DelayDisplayActivityScreen
import org.junit.Rule
import org.junit.Test

class WaitUntilTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<DelayDisplayActivity>()

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true
    )

    @Test
    fun testWaitUntilAssertIsDisplayed() {
        onComposeScreen<DelayDisplayActivityScreen> {
            mySimpleText {
                assertIsNotDisplayed()
                waitUntil {
                    assertIsDisplayed()
                }
                assertIsDisplayed()
            }
        }
    }
}
