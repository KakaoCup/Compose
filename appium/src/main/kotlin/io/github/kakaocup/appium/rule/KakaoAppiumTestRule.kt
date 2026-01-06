package io.github.kakaocup.appium.rule

import io.appium.java_client.AppiumDriver
import io.github.kakaocup.appium.KakaoAppium
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class KakaoAppiumTestRule(
    val driver: AppiumDriver? = null,
) : TestRule {
    override fun apply(base: Statement, description: Description): Statement =
        object : Statement() {
            override fun evaluate() {
                val preEvaluatedDriver = KakaoAppium.Global.driver
                driver?.let { KakaoAppium.Global.driver = it }

                base.evaluate()

                KakaoAppium.Global.driver = preEvaluatedDriver
            }
        }
}

