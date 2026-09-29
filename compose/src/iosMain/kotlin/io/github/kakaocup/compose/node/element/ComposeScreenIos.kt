package io.github.kakaocup.compose.node.element

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider

@PublishedApi
internal actual inline fun <reified T : ComposeScreen<T>> instantiateScreen(
    semanticsProvider: SemanticsNodeInteractionsProvider,
): T = throw UnsupportedOperationException(
    "Reflection-based onComposeScreen<T> is not supported on iOS. " +
        "Instantiate the screen directly: MyScreen(provider).apply { /* … */ }."
)

@PublishedApi
internal actual inline fun <reified T : ComposeScreen<T>> instantiateScreen(): T =
    throw UnsupportedOperationException(
        "Reflection-based onComposeScreen<T> is not supported on iOS. " +
            "Instantiate the screen directly: MyScreen().apply { /* … */ }."
    )
