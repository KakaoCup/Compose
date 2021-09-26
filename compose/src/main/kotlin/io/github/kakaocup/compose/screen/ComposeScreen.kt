package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
@Suppress("UNCHECKED_CAST")
open class ComposeScreen<out T : ComposeScreen<T>>
    (val composeTestRule: AndroidComposeTestRule<*, *>) {

    operator fun invoke(function: T.() -> Unit) {
        function.invoke(this as T)
    }

    companion object {
        inline fun <reified T : ComposeScreen<T>> onComposeScreen(
            composeTestRule: AndroidComposeTestRule<*, *>,
            noinline function: T.() -> Unit
        ): T {
            return T::class.java
                .getDeclaredConstructor(AndroidComposeTestRule::class.java)
                .newInstance(composeTestRule)
                .apply { this(function) }
        }
    }
}