package io.github.kakaocup.compose.test

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import io.github.kakaocup.compose.sample.BackgroundSemanticsActivity
import io.github.kakaocup.compose.screen.SemanticsExtensionActivityScreen
import io.github.kakaocup.compose.semantics.assertBackgroundAlphaEquals
import io.github.kakaocup.compose.semantics.assertBackgroundBrushEquals
import io.github.kakaocup.compose.semantics.assertBackgroundColorEquals
import io.github.kakaocup.compose.semantics.assertBackgroundShapeEquals
import org.junit.Rule
import org.junit.Test

class BackgroundSemanticsExtensionTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<BackgroundSemanticsActivity>()

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true
    )

    @Test
    fun testColor() {
        onComposeScreen<SemanticsExtensionActivityScreen> {
            backgroundColorAndShapeBox {
                assertBackgroundColorEquals(Color.Black)
            }
        }
    }

    @Test
    fun testColorString() {
        onComposeScreen<SemanticsExtensionActivityScreen> {
            backgroundColorAndShapeBox {
                assertBackgroundColorEquals("#000000")
            }
        }
    }

    @Test
    fun testColorLong() {
        onComposeScreen<SemanticsExtensionActivityScreen> {
            backgroundColorAndShapeBox {
                assertBackgroundColorEquals(0xFF000000)
            }
        }
    }

    @Test
    fun testShape() {
        onComposeScreen<SemanticsExtensionActivityScreen> {
            backgroundColorAndShapeBox {
                assertBackgroundShapeEquals(RectangleShape)
            }
        }
    }

    @Test
    fun testBrush() {
        onComposeScreen<SemanticsExtensionActivityScreen> {
            backgroundBrushAndShapeBox {
                assertBackgroundBrushEquals(Brush.linearGradient(listOf(Color.Green, Color.Red)))
            }
        }
    }

    @Test
    fun testAlpha() {
        onComposeScreen<SemanticsExtensionActivityScreen> {
            backgroundBrushAndShapeBox {
                assertBackgroundAlphaEquals(0.5f)
            }
        }
    }
}
