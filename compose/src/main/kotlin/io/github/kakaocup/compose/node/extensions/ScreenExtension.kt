package io.github.kakaocup.compose.node.extensions

import io.github.kakaocup.compose.node.KNode
import io.github.kakaocup.compose.node.ViewBuilder
import io.github.kakaocup.compose.screen.ComposeScreen

fun ComposeScreen<*>.onNode(function: ViewBuilder.() -> Unit) = KNode(this, function)