package io.github.kakaocup.compose.utilities

import androidx.compose.ui.graphics.Color

internal fun getComposeColor(color: String): Color {
    val normalizeColor = if (color.contains("#")) color else "#$color"
    return Color(android.graphics.Color.parseColor(normalizeColor))
}
