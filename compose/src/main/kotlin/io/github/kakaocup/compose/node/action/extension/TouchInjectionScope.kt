package io.github.kakaocup.compose.node.action.extension

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.TouchInjectionScope
import io.github.kakaocup.compose.node.action.option.Offsets

internal fun TouchInjectionScope.createOffset(
    offset: Offsets,
    offsetX: Float? = null,
    offsetY: Float? = null
): Offset {
    return when (offset) {
        Offsets.CENTER -> center
        Offsets.CENTER_LEFT -> centerLeft.copy(x = 1f)
        Offsets.CENTER_RIGHT -> centerRight
        Offsets.TOP_CENTER -> topCenter.copy(y = 1f)
        Offsets.TOP_LEFT -> topLeft.copy(x = 1f, y = 1f)
        Offsets.TOP_RIGHT -> topRight.copy(y = 1f)
        Offsets.BOTTOM_CENTER -> bottomCenter
        Offsets.BOTTOM_LEFT -> bottomLeft.copy(x = 1f)
        Offsets.BOTTOM_RIGHT -> bottomRight
    }.run {
        copy(x = x + (offsetX ?: 0F), y = y + (offsetY ?: 0F))
    }
}