package io.github.kakaocup.appium.node.builder.patform

import io.appium.java_client.AppiumBy

class AndroidDataMatcher : DataMatcher() {
    fun hasId(id: String) = addFilter(AppiumBy.ByAndroidDataMatcher.id(id))
    fun hasTag(tag: String) = addFilter(AppiumBy.ByAndroidDataMatcher.androidViewTag(tag))
}