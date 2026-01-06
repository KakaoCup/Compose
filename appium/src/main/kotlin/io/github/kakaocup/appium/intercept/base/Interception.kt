package io.github.kakaocup.appium.intercept.base

data class Interception<T>(val isOverride: Boolean, val interceptor: T)
