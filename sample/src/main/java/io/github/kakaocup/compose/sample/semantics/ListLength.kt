package io.github.kakaocup.compose.sample.semantics

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics

val ListLengthSemanticKey = SemanticsPropertyKey<Int>("ListLength")
var SemanticsPropertyReceiver.listLength by ListLengthSemanticKey
fun Modifier.listLengthSemantic(length: Int): Modifier {
    return semantics { listLength = length }
}
