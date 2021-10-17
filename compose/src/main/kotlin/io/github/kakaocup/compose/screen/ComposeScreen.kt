package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeTestRule

@Suppress("UNCHECKED_CAST")
open class ComposeScreen<out T : ComposeScreen<T>>(
    val composeTestRule: ComposeTestRule
) {

    operator fun invoke(function: T.() -> Unit) {
        function.invoke(this as T)
    }

    companion object {
        inline fun <reified T : ComposeScreen<T>> onComposeScreen(
            androidComposeTestRule: AndroidComposeTestRule<*, *>,
            noinline function: T.() -> Unit
        ): T {
            return T::class.java
                .getDeclaredConstructor(ComposeTestRule::class.java)
                .newInstance(androidComposeTestRule)
                .apply { this(function) }
        }

        inline fun <reified T : ComposeScreen<T>> onComposeScreen(
            composeTestRule: ComposeTestRule,
            noinline function: T.() -> Unit
        ): T {
            return T::class.java
                .getDeclaredConstructor(ComposeTestRule::class.java)
                .newInstance(composeTestRule)
                .apply { this(function) }
        }
    }
}