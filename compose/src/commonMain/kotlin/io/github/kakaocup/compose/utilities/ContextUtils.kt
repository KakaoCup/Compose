package io.github.kakaocup.compose.utilities

/**
 * Resolves an Android string resource id to its localized string.
 *
 * Android: looks up via instrumentation context.
 * iOS: throws [UnsupportedOperationException] — iOS has no Android-style resource ids;
 * pass string literals (or use Compose Resources) instead.
 */
expect fun getResourceString(resId: Int): String
