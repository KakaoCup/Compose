package io.github.kakaocup.compose.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kakaocup.compose.sample.semantics.imageContentSemantic
import io.github.kakaocup.compose.sample.semantics.listItemIndexSemantic
import io.github.kakaocup.compose.sample.semantics.listLength

/**
 * Sample with not scrollable list.
 */
@Composable
fun NotScrollableListScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var selectedTabId by remember {
            mutableStateOf(TabBarItem.Search.id)
        }

        CustomTabBar(
            selectedTabId = selectedTabId,
            onSelect = { selectedTabId = it },
            modifier = Modifier
                .align(Alignment.BottomStart)
        )
    }
}

/**
 * Example of not scrollable list.
 */
@Composable
private fun CustomTabBar(
    selectedTabId: String,
    onSelect: (id: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 49.dp)
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )

        // Warning!
        // There is no `Modifier.horizontalScroll`, so there is no `AccessibilityAction.ScrollBy` in this container.
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .semantics {
                    testTag = "tabbar_container"
                    listLength = TabBarItem.entries.size
                },
        ) {
            TabBarItem.entries.forEachIndexed { index, tabBarItem ->
                TabBarItemContent(
                    item = tabBarItem,
                    selectedTabId = selectedTabId,
                    onSelect = onSelect,
                    modifier = Modifier
                        .weight(1f)
                        .listItemIndexSemantic(index)
                )
            }
        }
    }
}

@Composable
private fun TabBarItemContent(
    item: TabBarItem,
    selectedTabId: String,
    onSelect: (id: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isSelected = item.id == selectedTabId

    Box(
        modifier = modifier
            .selectable(
                selected = isSelected,
                role = Role.Tab,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null,
                onClick = { onSelect.invoke(item.id) }
            ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 5.dp, bottom = 4.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val iconContent = if (isSelected) item.selectedIcon else item.unselectedIcon
                Icon(
                    imageVector = iconContent,
                    contentDescription = null,
                    modifier = Modifier
                        .testTag("tabbar_item_icon")
                        .imageContentSemantic(iconContent)
                )

                if (item.counter != null) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${item.counter}",
                        color = Color.White,
                        modifier = Modifier
                            .background(Color.Red)
                    )
                }
            }
            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = item.text,
                style = MaterialTheme.typography.button,
                color = if (isSelected) Color.Black else Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .testTag("tabbar_item_text"),
            )
        }
    }
}

private enum class TabBarItem(
    val id: String,
    val text: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val counter: Int? = null,
) {
    Search(
        id = "0",
        text = "Search",
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
    ),
    Favorites(
        id = "1",
        text = "Favorites",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        counter = 100,
    ),
    Responses(
        id = "2",
        text = "Responses",
        selectedIcon = Icons.Filled.Email,
        unselectedIcon = Icons.Outlined.Email,
    ),
    Chats(
        id = "3",
        text = "Chats",
        selectedIcon = Icons.Filled.Send,
        unselectedIcon = Icons.Outlined.Send,
        counter = 9,
    ),
    Profile(
        id = "4",
        text = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
    ),
}

@Preview
@Composable
private fun PreviewNotScrollableScreen() {
    MaterialTheme {
        NotScrollableListScreen()
    }
}
