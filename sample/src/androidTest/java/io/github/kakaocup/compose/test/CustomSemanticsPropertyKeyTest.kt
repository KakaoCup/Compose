package io.github.kakaocup.compose.test

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import io.github.kakaocup.compose.sample.CustomSemanticKey
import io.github.kakaocup.compose.sample.CustomSemanticPropertyKeyActivity
import io.github.kakaocup.compose.screen.CustomSemanticsPropertyKeyActivityScreen
import org.junit.Rule
import org.junit.Test

class CustomSemanticsPropertyKeyTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<CustomSemanticPropertyKeyActivity>()

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true
    )

    @Test
    fun testCustomSemanticsPropertyKey() {
        onComposeScreen<CustomSemanticsPropertyKeyActivityScreen> {
            view {
                assertHasProperty("Any value", CustomSemanticKey, "custom semantic property key")
            }
        }
    }
}
