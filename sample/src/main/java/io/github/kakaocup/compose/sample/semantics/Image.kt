package io.github.kakaocup.compose.sample.semantics

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics

val ImageContentSemanticKey = SemanticsPropertyKey<Any>("ImageContent")
var SemanticsPropertyReceiver.imageContent by ImageContentSemanticKey
fun Modifier.imageContentSemantic(imageContent: Any): Modifier {
    return semantics { this.imageContent = imageContent }
}
