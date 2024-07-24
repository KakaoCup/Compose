package io.github.kakaocup.compose.test.list

import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createComposeRule
import io.github.kakaocup.compose.KakaoCompose
import io.github.kakaocup.compose.SampleNoActivityTest
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import io.github.kakaocup.compose.sample.NotScrollableListScreen
import io.github.kakaocup.compose.sample.semantics.ImageContentSemanticKey
import io.github.kakaocup.compose.screen.list.NotScrollableListComposeScreen
import org.junit.Rule
import org.junit.Test

class NotScrollableListTest : SampleNoActivityTest() {

    @Test
    fun test() {
        composeTestRule.setContent {
            MaterialTheme {
                NotScrollableListScreen()
            }
        }

        onComposeScreen<NotScrollableListComposeScreen>() {
            notScrollableListNode {
                assertIsDisplayed()

                assertLengthEquals(5)

                itemAt(1) {
                    performClick()
                    assertIsSelected()
                }

                childAt<NotScrollableListComposeScreen.TabBarListItemNode>(2) {
                    text {
                        assertTextEquals("Responses")
                    }
                    icon {
                        assert(
                            SemanticsMatcher.expectValue(ImageContentSemanticKey, Icons.Outlined.Email)
                        )
                    }
                }

                itemWith({
                    hasAnyDescendant(
                        androidx.compose.ui.test.hasText("Profile")
                    )
                }) {
                    assertIsNotSelected()
                    performClick()
                    assertIsSelected()
                }
            }
        }
    }

}