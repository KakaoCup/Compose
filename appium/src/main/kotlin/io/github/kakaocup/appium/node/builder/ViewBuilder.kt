package io.github.kakaocup.appium.node.builder

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.github.kakaocup.appium.node.builder.patform.AndroidDataMatcher
import io.github.kakaocup.appium.node.builder.patform.DataMatcher
import io.github.kakaocup.appium.node.builder.patform.IOSDataMatcher
import io.github.kakaocup.appium.node.core.AppiumMarker
import org.openqa.selenium.By
import org.openqa.selenium.support.pagefactory.ByAll

@AppiumMarker
class ViewBuilder {
    private enum class Platform {
        ANDROID,
        IOS,
        DEFAULT,
    }

    private val byPlatformList = mutableMapOf<Platform, List<By>>()

    private var position = 0

    fun android(android: AndroidDataMatcher.() -> Unit) {
        val matcher = AndroidDataMatcher().apply(android)
        byPlatformList[Platform.ANDROID] = matcher.matchers
    }

    fun ios(ios: IOSDataMatcher.() -> Unit) {
        val matcher = IOSDataMatcher().apply(ios)
        byPlatformList[Platform.IOS] = matcher.matchers
    }

    fun default(default: DataMatcher.() -> Unit) {
        val matcher = DataMatcher().apply(default)
        byPlatformList[Platform.DEFAULT] = matcher.matchers
    }

    /**
     * Returns whether the node matches exactly to the given custom matcher.
     */
    fun hasPosition(position: Int) {
        this.position = position
    }

    fun build(driver: AppiumDriver): WebElementMatcher {
        byPlatformList[
            when (driver) {
                is AndroidDriver -> Platform.ANDROID
                is IOSDriver -> Platform.IOS
                else -> Platform.DEFAULT
            }]
            ?: byPlatformList[Platform.DEFAULT]?.let {
                return WebElementMatcher(
                    by = ByAll(*it.toTypedArray()),
                    position = position
                )
            }
        throw ViewBuilderException("Please set matchers for your platform or use default")
    }
}

private class ViewBuilderException(message: String) : RuntimeException(message)
