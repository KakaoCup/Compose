package io.github.kakaocup.compose.sample

import androidx.compose.ui.window.ComposeUIViewController

/**
 * iOS entry point — consume from an iosApp Xcode project as
 * `MainViewControllerKt.MainViewController()`.
 */
fun MainViewController() = ComposeUIViewController { MainScreen() }
