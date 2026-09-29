package io.github.kakaocup.compose.node.element

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider

@PublishedApi
internal actual inline fun <reified T : ComposeScreen<T>> instantiateScreen(
    semanticsProvider: SemanticsNodeInteractionsProvider,
): T = T::class.java
    .getDeclaredConstructor(SemanticsNodeInteractionsProvider::class.java)
    .newInstance(semanticsProvider)

@PublishedApi
internal actual inline fun <reified T : ComposeScreen<T>> instantiateScreen(): T =
    T::class.java.getDeclaredConstructor().newInstance()
