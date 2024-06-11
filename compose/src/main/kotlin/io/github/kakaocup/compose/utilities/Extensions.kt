package io.github.kakaocup.compose.utilities

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.KakaoCompose
import io.github.kakaocup.compose.exception.KakaoComposeException

fun SemanticsNodeInteractionsProvider?.orGlobal(): SemanticsNodeInteractionsProvider? =
    this ?: KakaoCompose.Global.semanticsProvider

fun SemanticsNodeInteractionsProvider?.checkNotNull(): SemanticsNodeInteractionsProvider =
    this ?: throw KakaoComposeException("SemanticsProvider is null: Provide via constructor or use KakaoComposeTestRule")