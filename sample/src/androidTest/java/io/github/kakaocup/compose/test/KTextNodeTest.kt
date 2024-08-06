package io.github.kakaocup.compose.test

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import io.github.kakaocup.compose.sample.TextActivity
import io.github.kakaocup.compose.screen.TextActivityScreen
import org.junit.Rule
import org.junit.Test

class KTextNodeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<TextActivity>()

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true
    )

    @Test
    fun textTextColor() {
        onComposeScreen<TextActivityScreen> {
            textWithoutStyle {
                assertTextColorEquals(Color.Black)
            }

            textWithStyle {
                assertTextColorEquals(Color.Black)
            }

            textWithSemantic {
                assertTextColorEquals(Color.Black)
            }
        }
    }

    @Test
    fun textTextColorString() {
        onComposeScreen<TextActivityScreen> {
            textWithoutStyle {
                assertTextColorEquals("#000000")
            }

            textWithStyle {
                assertTextColorEquals("#000000")
            }

            textWithSemantic {
                assertTextColorEquals("#000000")
            }
        }
    }

    @Test
    fun textTextColorLong() {
        onComposeScreen<TextActivityScreen> {
            textWithoutStyle {
                assertTextColorEquals(0xFF000000)
            }

            textWithStyle {
                assertTextColorEquals(0xFF000000)
            }

            textWithSemantic {
                assertTextColorEquals(0xFF000000)
            }
        }
    }
}
