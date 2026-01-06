package io.github.kakaocup.appium.node.element

import io.appium.java_client.AppiumDriver
import io.github.kakaocup.appium.node.builder.ViewBuilder
import io.github.kakaocup.appium.node.builder.WebElementMatcher
import io.github.kakaocup.appium.node.core.BaseNode

open class KWebElement : BaseNode<KWebElement> {

    constructor(
        driver: AppiumDriver? = null,
        nodeMatcher: WebElementMatcher,
        parentNode: BaseNode<*>? = null,
    ) : super(driver, nodeMatcher, parentNode)

    constructor(
        driver: AppiumDriver? = null,
        viewBuilderAction: ViewBuilder.() -> Unit,
    ) : super(driver, viewBuilderAction)

    constructor(
        driver: AppiumDriver? = null,
        nodeMatcher: WebElementMatcher,
    ) : super(driver, nodeMatcher)
}