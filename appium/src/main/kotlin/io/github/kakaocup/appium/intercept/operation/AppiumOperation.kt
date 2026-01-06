package io.github.kakaocup.appium.intercept.operation

/**
 * Responsible for executing an interaction on the element of Jetpack Compose
 */
interface AppiumOperation<View> {

    val type: AppiumOperationType

    val description: String?

    fun execute(innerView: View)
}