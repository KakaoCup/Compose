package io.github.kakaocup.compose.node.extensions

import io.github.kakaocup.compose.node.KNode
import io.github.kakaocup.compose.node.ViewBuilder
import io.github.kakaocup.compose.screen.ComposeScreen

fun ComposeScreen<*>.onNode(useUnmergedTree : Boolean = false,
                            function: ViewBuilder.() -> Unit) = KNode(this, useUnmergedTree, function)