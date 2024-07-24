package io.github.kakaocup.compose.sample.semantics

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics

val ListItemIndexSemanticKey = SemanticsPropertyKey<Int>("ListItemIndex")
var SemanticsPropertyReceiver.listItemIndex by ListItemIndexSemanticKey
fun Modifier.listItemIndexSemantic(index: Int): Modifier {
    return semantics { listItemIndex = index }
}
