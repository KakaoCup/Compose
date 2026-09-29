package io.github.kakaocup.compose.intercept.operation

import androidx.compose.ui.test.SemanticsNodeInteraction

interface ComposeAction : ComposeOperation<SemanticsNodeInteraction>

interface ComposeAssertion : ComposeOperation<SemanticsNodeInteraction>

fun produceComposeAction(
    type: ComposeOperationType,
    description: String?,
    action: SemanticsNodeInteraction.() -> Unit
): ComposeAction = object : ComposeAction {
    override val type: ComposeOperationType = type
    override val description: String? = description
    override fun execute(innerView: SemanticsNodeInteraction) = action.invoke(innerView)
    override fun toString(): String {
        return "ComposeAction (type=$type, description=$description)"
    }
}

fun produceComposeAssertion(
    type: ComposeOperationType,
    description: String?,
    action: SemanticsNodeInteraction.() -> Unit
): ComposeAssertion = object : ComposeAssertion {
    override val type: ComposeOperationType = type
    override val description: String? = description
    override fun execute(innerView: SemanticsNodeInteraction) = action.invoke(innerView)
    override fun toString(): String {
        return "ComposeAssertion (type=$type, description=$description)"
    }
}