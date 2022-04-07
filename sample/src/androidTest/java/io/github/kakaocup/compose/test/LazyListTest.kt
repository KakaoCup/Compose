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
                childAt<LazyListHeaderNode>(0) {
                    title.assertTextEquals("Items from 1 to 10")
                }
                childAt<LazyListItemNode>(1) {
                    title.assertTextEquals("Item 1")
                }
                childAt<LazyListItemNode>(10) {
                    title.assertTextEquals("Item 10")
                }
                childAt<LazyListHeaderNode>(22) {
                    title.assertTextEquals("Items from 21 to 30")
                }
                childAt<LazyListItemNode>(23) {
                    title.assertTextEquals("Item 21")
                }
                childAt<LazyListItemNode>(32) {
                    title.assertTextEquals("Item 30")
                }
            }
        }
    }
}