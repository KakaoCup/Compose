package io.github.kakaocup.compose.test

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import io.github.kakaocup.compose.LazyListScreen
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.screen.LazyListHeaderNode
import io.github.kakaocup.compose.screen.LazyListItemNode
import io.github.kakaocup.compose.screen.LazyListScreen
import org.junit.Rule
import org.junit.Test

class LazyListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    @OptIn(ExperimentalTestApi::class)
    fun lazyListTest() {
        composeTestRule.setContent {
            MaterialTheme {
                LazyListScreen()
            }
        }

        onComposeScreen<LazyListScreen>(composeTestRule) {
            list {
                firstChild<LazyListHeaderNode> {
                    title.assertTextEquals("Items from 1 to 10")
                }
                childWith<LazyListItemNode> {
                    hasText("Item 1")
                } perform {
                    assertTextEquals("Item 1")
                }
                childAt<LazyListItemNode>(10) {
                    assertTextEquals("Item 10")
                }
                childWith<LazyListHeaderNode> {
                    hasAnyChild(androidx.compose.ui.test.hasText("Items from 21 to 30"))
                } perform {
                    title.assertTextEquals("Items from 21 to 30")
                }
                childAt<LazyListItemNode>(23) {
                    assertTextEquals("Item 21")
                }
                childAt<LazyListItemNode>(32) {
                    assertTextEquals("Item 30")
                }
            }
        }
    }
}
