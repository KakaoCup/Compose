package io.github.kakaocup.compose.intercept.interaction

/**
 * Base Interaction interface for Kakao-Compose
 *
 * Inspired by the idea from legacy UI system where all interactions with a View are possible through only two methods: `check` and `perform`.
 * Such approach allows to introduce Interceptors simply.
 *
 */
interface Interaction<ASSERTION, ACTION> {

    fun check(assertion: ASSERTION)

    fun perform(action: ACTION)
}