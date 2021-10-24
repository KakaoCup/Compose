package io.github.kakaocup.compose.intercept.operation

import androidx.compose.ui.test.SemanticsNodeInteraction

typealias ComposeAction = (SemanticsNodeInteraction) -> Unit

typealias ComposeAssertion = (SemanticsNodeInteraction) -> Unit