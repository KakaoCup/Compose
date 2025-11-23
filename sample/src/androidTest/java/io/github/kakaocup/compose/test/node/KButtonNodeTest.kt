package io.github.kakaocup.compose.test.node

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import io.github.kakaocup.compose.sample.ButtonActivity
import io.github.kakaocup.compose.screen.ButtonActivityScreen
import org.junit.Rule
import org.junit.Test

class KButtonNodeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ButtonActivity>()

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true
    )

    @Test
    fun testAssertPaddingValuesEquals() {
        onComposeScreen<ButtonActivityScreen> {
            val padding = PaddingValues(
                start = 32.dp,
                end = 32.dp,
                top = 32.dp,
                bottom = 32.dp
            )

            button {
                assertPaddingValuesEquals(padding)
            }

            outlinedButton {
                assertPaddingValuesEquals(padding)
            }

            textButton {
                assertPaddingValuesEquals(padding)
            }
        }
    }

    @Test
    fun testAssertBorderStrokeEquals() {
        onComposeScreen<ButtonActivityScreen> {
            val borderStroke = BorderStroke(12.dp, Color.Green)

            button {
                assertBorderStrokeEquals(borderStroke)
            }

            outlinedButton {
                assertBorderStrokeEquals(borderStroke)
            }

            textButton {
                assertBorderStrokeEquals(borderStroke)
            }
        }
    }

    @Test
    fun testAssertShapeEquals() {
        onComposeScreen<ButtonActivityScreen> {
            val shape = RoundedCornerShape(24.dp)

            button {
                assertShapeEquals(shape)
            }

            outlinedButton {
                assertShapeEquals(shape)
            }

            textButton {
                assertShapeEquals(shape)
            }
        }
    }
}
