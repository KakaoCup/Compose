package io.github.kakaocup.appium.node.core

import io.appium.java_client.AppiumDriver
import io.github.kakaocup.appium.intercept.delegate.AppiumInterceptable
import io.github.kakaocup.appium.intercept.delegate.WebElementDelegate
import io.github.kakaocup.appium.node.action.TextActions
import io.github.kakaocup.appium.node.action.WebElementActions
import io.github.kakaocup.appium.node.assertion.WebElementAssertions
import io.github.kakaocup.appium.node.builder.ViewBuilder
import io.github.kakaocup.appium.node.builder.WebElementMatcher
import io.github.kakaocup.appium.node.builder.WebElementProvider
import io.github.kakaocup.appium.utilities.checkNotNull
import org.openqa.selenium.By
import org.openqa.selenium.support.pagefactory.ByAll

@AppiumMarker
abstract class BaseNode<out T : BaseNode<T>> constructor(
    var driver: AppiumDriver? = null,
    private var webElementMatcher: WebElementMatcher? = null,
    private var parentNode: BaseNode<*>? = null,
) : KDSL<T>,
    WebElementAssertions,
    WebElementActions,
    TextActions,
    AppiumInterceptable {

    constructor(
        driver: AppiumDriver? = null,
        viewBuilderAction: ViewBuilder.() -> Unit,
    ) : this(
        driver = driver,
        webElementMatcher = ViewBuilder().apply(viewBuilderAction).build(driver.checkNotNull()),
        parentNode = null
    )

    constructor(
        driver: AppiumDriver? = null,
        webElementMatcher: WebElementMatcher,
    ) : this(
        driver = driver,
        webElementMatcher = webElementMatcher,
        parentNode = null
    )

    override val delegate: WebElementDelegate by lazy(LazyThreadSafetyMode.NONE) {
        WebElementDelegate(
            nodeProvider = WebElementProvider(
                webElementMatcher = WebElementMatcher(
                    by = combineSemanticMatchers(),
                    position = 0,
                ),
                driver = driver.checkNotNull()
            ),
            parentDelegate = parentNode?.delegate
        )
    }

    /***
     * Combines semantic matchers from all ancestor nodes
     */
    private fun combineSemanticMatchers(): By {
        val semanticsMatcherList = mutableListOf<WebElementMatcher>()
        var parent = this.parentNode

        /*
        while (parent != null) {
            semanticsMatcherList.add()
            semanticsMatcherList.add(hasAnyAncestor(parent.nodeMatcher.checkNotNull().matcher))
            parent = parent.parentNode
        }

         */
        semanticsMatcherList.add(this.webElementMatcher.checkNotNull())

        return ByAll(*semanticsMatcherList.map { it.by }.toTypedArray())
    }
}
