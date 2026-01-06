package io.github.kakaocup.appium.utilities

import io.appium.java_client.AppiumDriver
import io.github.kakaocup.appium.KakaoAppium
import io.github.kakaocup.appium.exception.KakaoAppiumException
import io.github.kakaocup.appium.node.builder.WebElementMatcher

fun AppiumDriver?.orGlobal(): AppiumDriver? =
    this ?: KakaoAppium.Global.driver

fun AppiumDriver?.checkNotNull(): AppiumDriver =
    this
        ?: throw KakaoAppiumException("Appium Driver is null: Provide via constructor or use KakaoAppiumTestRule")

fun WebElementMatcher?.checkNotNull(): WebElementMatcher =
    this
        ?: throw KakaoAppiumException("NodeMatcher is null: Provide via constructor or use 'initSemantics' method")