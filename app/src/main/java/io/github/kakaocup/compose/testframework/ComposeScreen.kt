package io.github.kakaocup.compose.testframework

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.fragment.app.FragmentActivity
import androidx.test.ext.junit.rules.ActivityScenarioRule

@Suppress("UNCHECKED_CAST")
open class ComposeScreen<out T : ComposeScreen<T, *>, ACTIVITY : FragmentActivity>
    (val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<ACTIVITY>, ACTIVITY>)
{

    operator fun invoke(function: T.() -> Unit) {
        function.invoke(this as T)
    }

    companion object {
        inline fun <reified T : ComposeScreen<T,*>> onComposeScreen(
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