package io.github.kakaocup.compose.extensions

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.junit4.ComposeTestRule
import io.github.kakaocup.compose.screen.MainActivityScreen

class Screens(provider: SemanticsNodeInteractionsProvider) {
    val main = MainActivityScreen(provider)
}

val ComposeTestRule.screens: Screens
    get() = Screens(this)
