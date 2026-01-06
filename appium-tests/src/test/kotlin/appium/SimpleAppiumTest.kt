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
    private lateinit var driver: AppiumDriver

    @Before
    fun setUp() {
        val caps = DesiredCapabilities().apply {
            setCapability("platformName", "Android")
            setCapability("deviceName", "emulator-5554")
            setCapability("automationName", "UiAutomator2")
            setCapability("appPackage", "io.github.kakaocup.sample")
            setCapability("appActivity", "io.github.kakaocup.sample.MainActivity")
            setCapability("noReset", true)
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
        driver.quit()
    }
}