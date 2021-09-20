package io.github.kakaocup.compose.testframework

@Suppress("UNCHECKED_CAST")
interface KDSLView<T>{

    operator fun invoke(function: T.() -> Unit) {
        function(this as T)
    }

    infix fun perform(function: T.() -> Unit): T {
        function(this as T)
        return this
    }
}