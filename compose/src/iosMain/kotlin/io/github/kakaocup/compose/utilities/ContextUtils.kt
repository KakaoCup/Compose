package io.github.kakaocup.compose.utilities

actual fun getResourceString(resId: Int): String =
    throw UnsupportedOperationException(
        "Android string resource ids are not available on iOS. Use string literals " +
            "or wire up Compose Resources for cross-platform text."
    )
