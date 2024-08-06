package io.github.kakaocup.compose.test

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import io.github.kakaocup.compose.sample.IconActivity
import io.github.kakaocup.compose.sample.R
import io.github.kakaocup.compose.screen.IconActivityScreen
import org.junit.Rule
import org.junit.Test

class KIconNodeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<IconActivity>()

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true
    )

    @Test
    fun testContentDrawable() {
        onComposeScreen<IconActivityScreen> {
            iconDrawableRes {
                assertContentEquals(R.drawable.ic_android)
            }
        }
    }

    @Test
    fun testContentVector() {
        onComposeScreen<IconActivityScreen> {
            iconImageVector {
                assertContentEquals(Icons.Filled.AccountCircle)
            }
        }
    }

    @Test
    fun testContentColor() {
        onComposeScreen<IconActivityScreen> {
            iconDrawableRes {
                assertTintColorEquals(Color.Black)
            }

            iconImageVector {
                assertTintColorEquals(Color.Black)
            }
        }
    }

    @Test
    fun testContentColorString() {
        onComposeScreen<IconActivityScreen> {
            iconDrawableRes {
                assertTintColorEquals("#000000")
            }

            iconImageVector {
                assertTintColorEquals("#000000")
            }
        }
    }
    @Test
    fun testContentColorLong() {
        onComposeScreen<IconActivityScreen> {
            iconDrawableRes {
                assertTintColorEquals(0xFF000000)
            }

            iconImageVector {
                assertTintColorEquals(0xFF000000)
            }
        }
    }
}
