package io.github.kakaocup.compose.test.list

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.swipeDown
import io.github.kakaocup.compose.SampleNoActivityTest
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.sample.ScrollableLazyListScreen
import io.github.kakaocup.compose.screen.list.ScrollableLazyListComposeScreen
import org.junit.Test

class ScrollableLazyListTest : SampleNoActivityTest() {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun test() {
        composeTestRule.setContent {
            MaterialTheme {
                ScrollableLazyListScreen()
            }
        }

        onComposeScreen<ScrollableLazyListComposeScreen>() {
            lazyList.assertLengthEquals(0)

            pullToRefresh.performTouchInput {
                swipeDown(startY = 200f)
            }

            lazyList {
                assertLengthEquals(33)
                firstChild<ScrollableLazyListComposeScreen.ListHeaderNode> {
                    title.assertTextEquals("Items from 1 to 10")
                }

                itemWith(
                    { hasText("Item 1") }
                ) {
                    assertTextEquals("Item 1")
                }

                itemAt(10) {
                    assertTextEquals("Item 10")
                }
                childWith<ScrollableLazyListComposeScreen.ListHeaderNode>(
                    { hasAnyDescendant(androidx.compose.ui.test.hasText("Items from 21 to 30")) }
                ) {
                    title.assertTextEquals("Items from 21 to 30")
                }
                itemAt(23) {
                    assertTextEquals("Item 21")
                }
                itemAt(32) {
                    assertTextEquals("Item 30")
                }
            }

            lazyList.performScrollToIndex(0)

            pullToRefresh.performTouchInput {
                swipeDown(startY = 200f)
            }
            lazyList.assertLengthEquals(34)

        }
    }

}