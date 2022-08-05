package io.github.kakaocup.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun LazyListScreen() {
    val items = remember { getItems() }

    Scaffold(Modifier.testTag("LazyListScreen")) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .testTag("LazyList")
        ) {
            itemsIndexed(items) { index, item ->
                when (item) {
                    is LazyListItem.Header -> ListItemHeader(item, Modifier.lazyListItemPosition(index))
                    is LazyListItem.Item -> ListItemCell(item, Modifier.lazyListItemPosition(index))
                }
            }
        }
    }
}

@Composable
private fun ListItemHeader(item: LazyListItem.Header, modifier: Modifier = Modifier) {
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
private fun ListItemCell(item: LazyListItem.Item, modifier: Modifier = Modifier) {
    Text(
        item.title,
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("LazyListItemTitle")
            .then(modifier)
    )
}

private fun getItems(): List<LazyListItem> {
    val result = mutableListOf<LazyListItem>()

    repeat(30) { index ->
        if (index % 10 == 0) {
            result += LazyListItem.Header(title = "Items from ${index + 1} to ${index + 10}")
        }
        result += LazyListItem.Item("Item ${index + 1}")
    }

    return result
}

private sealed class LazyListItem {
    data class Header(val title: String) : LazyListItem()
    data class Item(val title: String) : LazyListItem()
}

val LazyListItemPosition = SemanticsPropertyKey<Int>("LazyListItemPosition")
var SemanticsPropertyReceiver.lazyListItemPosition by LazyListItemPosition

fun Modifier.lazyListItemPosition(position: Int): Modifier {
    return semantics { lazyListItemPosition = position }
}
