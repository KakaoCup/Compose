package io.github.kakaocup.compose.test

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.KakaoCompose
import io.github.kakaocup.compose.MainActivity
import io.github.kakaocup.compose.extensions.screens
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class InterceptorTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testKakaoComposeInterceptors() {
        val list = mutableListOf<String>()

        KakaoCompose {
            intercept {
                onComposeInteraction {
                    onAll { list.add("ALL") }
                    onCheck { _, _ -> list.add("CHECK") }
                    onPerform { _, _ -> list.add("PERFORM") }
                }
            }
        }

        composeTestRule.screens.main {
            myButton {
                assertIsDisplayed()
                performClick()
            }
        }

        Assert.assertEquals(mutableListOf("ALL", "CHECK", "ALL", "PERFORM"), list)
    }

    @Test
    fun testViewInterceptors() {
        val list = mutableListOf<String>()

        composeTestRule.screens.main {
            myButton {
                intercept {
                    onAll { list.add("ALL") }
                    onCheck { _, _ -> list.add("CHECK") }
                    onPerform { _, _ -> list.add("PERFORM") }
                }

                assertIsDisplayed()
                performClick()
            }
        }

        Assert.assertEquals(mutableListOf("ALL", "CHECK", "ALL", "PERFORM"), list)
    }

    @Test
    fun testNestedInterceptors() {
        val list = mutableListOf<String>()

        KakaoCompose {
            intercept {
                onComposeInteraction {
                    onAll { list.add("ALL_KAKAO") }
                    onCheck { _, _ -> list.add("CHECK_KAKAO") }
                    onPerform { _, _ -> list.add("PERFORM_KAKAO") }
                }
            }
        }

        composeTestRule.screens.main {
            myButton {
                intercept {
                    onAll { list.add("ALL_VIEW") }
                    onCheck { _, _ -> list.add("CHECK_VIEW") }
                    onPerform { _, _ -> list.add("PERFORM_VIEW") }
                }

                assertIsDisplayed()
                performClick()
            }
        }

        Assert.assertEquals(
            mutableListOf(
                "ALL_VIEW", "CHECK_VIEW", "ALL_KAKAO", "CHECK_KAKAO", "ALL_VIEW",
                "PERFORM_VIEW", "ALL_KAKAO", "PERFORM_KAKAO"
            ), list
        )
    }

    @Test
    fun testFullNestedInterceptors() {
        val list = mutableListOf<String>()

        KakaoCompose {
            intercept {
                onComposeInteraction {
                    onAll { list.add("ALL_KAKAO") }
                    onCheck { _, _ -> list.add("CHECK_KAKAO") }
                    onPerform { _, _ -> list.add("PERFORM_KAKAO") }
                }
            }
        }

        composeTestRule.screens.main {
            intercept {
                onAll { list.add("ALL_SCREEN") }
                onCheck { _, _ -> list.add("CHECK_SCREEN") }
                onPerform { _, _ -> list.add("PERFORM_SCREEN") }
            }

            myButton {
                intercept {
                    onAll { list.add("ALL_VIEW") }
                    onCheck { _, _ -> list.add("CHECK_VIEW") }
                    onPerform { _, _ -> list.add("PERFORM_VIEW") }
                }

                assertIsDisplayed()
                performClick()
            }
        }

        Assert.assertEquals(
            mutableListOf(
                "ALL_VIEW", "CHECK_VIEW", "ALL_SCREEN", "CHECK_SCREEN", "ALL_KAKAO", "CHECK_KAKAO",
                "ALL_VIEW", "PERFORM_VIEW", "ALL_SCREEN", "PERFORM_SCREEN", "ALL_KAKAO", "PERFORM_KAKAO"
            ), list
        )
    }

    @Test
    fun testOverridingInterceptors() {
        val list = mutableListOf<String>()

        KakaoCompose {
            intercept {
                onComposeInteraction {
                    onAll { list.add("ALL_KAKAO") }
                    onCheck { _, _ -> list.add("CHECK_KAKAO") }
                    onPerform { _, _ -> list.add("PERFORM_KAKAO") }
                }
            }
        }

        composeTestRule.screens.main {
            myButton {
                intercept {
                    onAll { list.add("ALL_VIEW") }

                    onCheck(true) { interaction, assertion ->
                        list.add("CHECK_VIEW")
                        interaction.check(assertion)
                    }

                    onPerform(true) { interaction, action ->
                        list.add("PERFORM_VIEW")
                        interaction.perform(action)
                    }
                }

                assertIsDisplayed()
                performClick()
            }
        }

        Assert.assertEquals(
            mutableListOf("ALL_VIEW", "CHECK_VIEW", "ALL_VIEW", "PERFORM_VIEW"),
            list
        )
    }
}