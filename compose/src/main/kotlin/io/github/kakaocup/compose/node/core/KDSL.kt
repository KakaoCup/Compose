package io.github.kakaocup.compose.node.core

@Suppress("UNCHECKED_CAST")
interface KDSL<T> {

    operator fun invoke(function: T.() -> Unit) {
        function(this as T)
    }

    infix fun perform(function: T.() -> Unit): T {
        function(this as T)
        return this
    }
}