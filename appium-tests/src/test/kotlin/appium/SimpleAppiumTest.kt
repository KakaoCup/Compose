// kotlin
package io.github.kakaocup.appium

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.github.kakaocup.appium.node.element.AppiumScreen.Companion.onAppiumScreen
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

class SimpleAppiumTest {
    private var driver: AppiumDriver? = null

    @Before
    fun setUp() {
        val caps = DesiredCapabilities().apply {
            setCapability("platformName", "Android")
            setCapability("appium:deviceName", "emulator-5554")
            setCapability("appium:automationName", "UiAutomator2")
            setCapability("appium:appPackage", "io.github.kakaocup.appium.sample")
            setCapability("appium:appActivity", "io.github.kakaocup.appium.sample.MainActivity")
            setCapability("appium:noReset", true)
        }

        driver = AndroidDriver(URL("http://127.0.0.1:4723"), caps)
    }

    @Test
    fun sampleClickAndAssert() {
        onAppiumScreen<MainActivityScreen> {
            button1 {
                performClick()
            }
        }
    }

    @After
    fun tearDown() {
        driver?.quit()
    }
}