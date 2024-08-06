package io.github.kakaocup.compose.utilities

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.KakaoCompose
import io.github.kakaocup.compose.exception.KakaoComposeException
import io.github.kakaocup.compose.node.builder.NodeMatcher

fun SemanticsNodeInteractionsProvider?.orGlobal(): SemanticsNodeInteractionsProvider? =
    this ?: KakaoCompose.Global.semanticsProvider

fun SemanticsNodeInteractionsProvider?.checkNotNull(): SemanticsNodeInteractionsProvider =
    this ?: throw KakaoComposeException("SemanticsProvider is null: Provide via constructor or use KakaoComposeTestRule")

fun NodeMatcher?.checkNotNull(): NodeMatcher =
    this ?: throw KakaoComposeException("NodeMatcher is null: Provide via constructor or use 'initSemantics' method")