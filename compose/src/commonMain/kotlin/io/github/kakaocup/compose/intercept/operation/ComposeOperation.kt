package io.github.kakaocup.compose.intercept.operation

/**
 * Responsible for executing an interaction on the element of Jetpack Compose
 */
interface ComposeOperation<View> {

    val type: ComposeOperationType

    val description: String?

    fun execute(innerView: View)
}