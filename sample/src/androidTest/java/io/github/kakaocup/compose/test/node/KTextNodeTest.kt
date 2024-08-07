package io.github.kakaocup.compose.test.node

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
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
    fun textAssertTextColorEquals() {
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
    fun textAssertTextColorEqualsString() {
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
    fun textAssertTextColorEqualsLong() {
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

    @Test
    fun testAssertFontSizeEquals() {
        onComposeScreen<TextActivityScreen> {
            textWithAllFields {
                assertFontSizeEquals(16.sp)
            }
        }
    }

    @Test
    fun testAssertFontStyleEquals() {
        onComposeScreen<TextActivityScreen> {
            textWithAllFields {
                assertFontStyleEquals(FontStyle.Italic)
            }
        }
    }

    @Test
    fun testAssertFontWeightEquals() {
        onComposeScreen<TextActivityScreen> {
            textWithAllFields {
                assertFontWeightEquals(FontWeight.Bold)
            }
        }
    }

    @Test
    fun testAssertLetterSpacingEquals() {
        onComposeScreen<TextActivityScreen> {
            textWithAllFields {
                assertLetterSpacingEquals(4.sp)
            }
        }
    }

    @Test
    fun testAssertTextDecorationEquals() {
        onComposeScreen<TextActivityScreen> {
            textWithAllFields {
                assertTextDecorationEquals(TextDecoration.Underline)
            }
        }
    }

    @Test
    fun testAssertTextAlignEquals() {
        onComposeScreen<TextActivityScreen> {
            textWithAllFields {
                assertTextAlignEquals(TextAlign.Left)
            }
        }
    }

    @Test
    fun testAssertLineHeightEquals() {
        onComposeScreen<TextActivityScreen> {
            textWithAllFields {
                assertLineHeightEquals(6.sp)
            }
        }
    }

    @Test
    fun testAssertOverflowEquals() {
        onComposeScreen<TextActivityScreen> {
            textWithAllFields {
                assertOverflowEquals(TextOverflow.Ellipsis)
            }
        }
    }

    @Test
    fun testAssertSoftWrapEquals() {
        onComposeScreen<TextActivityScreen> {
            textWithAllFields {
                assertSoftWrapEquals(false)
            }
        }
    }

    @Test
    fun testAssertMaxLinesEquals() {
        onComposeScreen<TextActivityScreen> {
            textWithAllFields {
                assertMaxLinesEquals(10)
            }
        }
    }

    @Test
    fun testAssertMinLinesEquals() {
        onComposeScreen<TextActivityScreen> {
            textWithAllFields {
                assertMinLinesEquals(3)
            }
        }
    }
}
