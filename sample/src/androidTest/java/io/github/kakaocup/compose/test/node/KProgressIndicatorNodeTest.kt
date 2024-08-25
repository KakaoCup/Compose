package io.github.kakaocup.compose.test.node

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import io.github.kakaocup.compose.sample.ProgressIndicatorActivity
import io.github.kakaocup.compose.screen.ProgressIndicatorActivityScreen
import org.junit.Rule
import org.junit.Test

class KProgressIndicatorNodeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ProgressIndicatorActivity>()

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true
    )

    @Test
    fun testAssertColorEquals() {
        onComposeScreen<ProgressIndicatorActivityScreen> {
            linearWithProgress {
                assertColorEquals(Color.Green)
            }

            linearWithoutProgress {
                assertColorEquals(Color.Green)
            }

            circularWithProgress {
                assertColorEquals(Color.Green)
            }

            circularWithoutProgress {
                assertColorEquals(Color.Green)
            }
        }
    }

    @Test
    fun testAssertStrokeWidthEquals() {
        onComposeScreen<ProgressIndicatorActivityScreen> {
            circularWithProgress {
                assertStrokeWidthEquals(4.dp)
            }

            circularWithoutProgress {
                assertStrokeWidthEquals(4.dp)
            }
        }
    }

    @Test
    fun testAssertStrokeCapEquals() {
        onComposeScreen<ProgressIndicatorActivityScreen> {
            linearWithProgress {
                assertStrokeCapEquals(StrokeCap.Round)
            }

            linearWithoutProgress {
                assertStrokeCapEquals(StrokeCap.Round)
            }

            circularWithProgress {
                assertStrokeCapEquals(StrokeCap.Round)
            }

            circularWithoutProgress {
                assertStrokeCapEquals(StrokeCap.Round)
            }
        }
    }

    @Test
    fun testAssertBackgroundColorEquals() {
        onComposeScreen<ProgressIndicatorActivityScreen> {
            linearWithProgress {
                assertBackgroundColorEquals(Color.Blue)
            }

            linearWithoutProgress {
                assertBackgroundColorEquals(Color.Blue)
            }

            circularWithProgress {
                assertBackgroundColorEquals(Color.Blue)
            }

            circularWithoutProgress {
                assertBackgroundColorEquals(Color.Blue)
            }
        }
    }

    @Test
    fun testAssertProgressEquals() {
        onComposeScreen<ProgressIndicatorActivityScreen> {
            linearWithProgress {
                assertProgressEquals(0.3f)
            }

            circularWithProgress {
                assertProgressEquals(0.7f)
            }
        }
    }

}
