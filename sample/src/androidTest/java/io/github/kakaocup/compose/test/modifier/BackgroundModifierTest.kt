package io.github.kakaocup.compose.test.modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import io.github.kakaocup.compose.sample.BackgroundActivity
import io.github.kakaocup.compose.screen.BackgroundActivityScreen
import io.github.kakaocup.compose.foundation.assertBackgroundAlphaEquals
import io.github.kakaocup.compose.foundation.assertBackgroundBrushEquals
import io.github.kakaocup.compose.foundation.assertBackgroundColorEquals
import io.github.kakaocup.compose.foundation.assertBackgroundShapeEquals
import org.junit.Rule
import org.junit.Test

class BackgroundModifierTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<BackgroundActivity>()

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true
    )

    @Test
    fun testColor() {
        onComposeScreen<BackgroundActivityScreen> {
            backgroundColorAndShapeBox {
                assertBackgroundColorEquals(Color.Black)
            }
        }
    }

    @Test
    fun testColorString() {
        onComposeScreen<BackgroundActivityScreen> {
            backgroundColorAndShapeBox {
                assertBackgroundColorEquals("#000000")
            }
        }
    }

    @Test
    fun testColorLong() {
        onComposeScreen<BackgroundActivityScreen> {
            backgroundColorAndShapeBox {
                assertBackgroundColorEquals(0xFF000000)
            }
        }
    }

    @Test
    fun testShape() {
        onComposeScreen<BackgroundActivityScreen> {
            backgroundColorAndShapeBox {
                assertBackgroundShapeEquals(RectangleShape)
            }
        }
    }

    @Test
    fun testBrush() {
        onComposeScreen<BackgroundActivityScreen> {
            backgroundBrushAndShapeBox {
                assertBackgroundBrushEquals(Brush.linearGradient(listOf(Color.Green, Color.Red)))
            }
        }
    }

    @Test
    fun testAlpha() {
        onComposeScreen<BackgroundActivityScreen> {
            backgroundBrushAndShapeBox {
                assertBackgroundAlphaEquals(0.5f)
            }
        }
    }
}
