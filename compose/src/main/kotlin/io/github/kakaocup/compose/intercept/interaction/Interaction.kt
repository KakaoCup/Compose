package io.github.kakaocup.compose.intercept.interaction

interface Interaction<ASSERTION, ACTION> {

    fun check(assertion: ASSERTION)

    fun perform(action: ACTION)
}