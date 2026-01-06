package io.github.kakaocup.appium.node.builder.patform

import io.appium.java_client.AppiumBy

class IOSDataMatcher : DataMatcher() {
    fun hasNsPredicate(predicate: String) =
        addFilter(AppiumBy.ByIosNsPredicate.iOSNsPredicateString(predicate))
}