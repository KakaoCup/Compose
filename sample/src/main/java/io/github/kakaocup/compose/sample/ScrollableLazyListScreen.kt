package io.github.kakaocup.compose.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import io.github.kakaocup.compose.sample.semantics.listItemIndexSemantic
import io.github.kakaocup.compose.sample.semantics.listLengthSemantic

/**
 * Sample with scrollable lazy list.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScrollableLazyListScreen() {
    var items by remember { mutableStateOf(listOf<ScrollableLazyListItem>()) }
    var isRefreshing by remember { mutableStateOf(false) }

    Scaffold { scaffoldPadding ->
        val pullRefreshState = rememberPullRefreshState(
            refreshing = isRefreshing,
            onRefresh = {
                if (items.isEmpty()) {
                    items = getItems()
                } else {
                    items += ScrollableLazyListItem.Item("Item ${items.size + 1}")
                }
                isRefreshing = false
            }
        )

        Box(
            modifier = Modifier
                .padding(scaffoldPadding)
                .testTag("PullToRefresh")
                .pullRefresh(pullRefreshState)
        ) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .testTag("LazyList")
                    .listLengthSemantic(items.size)
            ) {
                itemsIndexed(items) { index, item ->
                    when (item) {
                        is ScrollableLazyListItem.Header -> ListItemHeader(item, Modifier.listItemIndexSemantic(index))
                        is ScrollableLazyListItem.Item -> ListItemCell(item, Modifier.listItemIndexSemantic(index))
                    }
                }
            }
            PullRefreshIndicator(
                state = pullRefreshState,
                refreshing = isRefreshing,
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
    }
}



@Composable
private fun ListItemHeader(item: ScrollableLazyListItem.Header, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Green)
            .then(modifier)
    ) {
        Text(
            item.title,
            Modifier
                .padding(8.dp)
                .testTag("LazyListHeaderTitle")
        )
    }
}

@Composable
private fun ListItemCell(item: ScrollableLazyListItem.Item, modifier: Modifier = Modifier) {
    Text(
        item.title,
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("LazyListItemTitle")
            .then(modifier)
    )
}

private fun getItems(): List<ScrollableLazyListItem> {
    val result = mutableListOf<ScrollableLazyListItem>()

    repeat(30) { index ->
        if (index % 10 == 0) {
            result += ScrollableLazyListItem.Header(title = "Items from ${index + 1} to ${index + 10}")
        }
        result += ScrollableLazyListItem.Item("Item ${index + 1}")
    }

    return result
}

private sealed class ScrollableLazyListItem {
    data class Header(val title: String) : ScrollableLazyListItem()
    data class Item(val title: String) : ScrollableLazyListItem()
}
