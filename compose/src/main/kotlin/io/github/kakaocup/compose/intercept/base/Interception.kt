package io.github.kakaocup.compose.intercept.base

data class Interception<T>(val isOverride: Boolean, val interceptor: T)
