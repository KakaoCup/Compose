package io.github.kakaocup.compose.rule

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.KakaoCompose
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class KakaoComposeTestRule(
    val semanticsProvider: SemanticsNodeInteractionsProvider?,
) : TestRule {
    override fun apply(base: Statement, description: Description): Statement =
        object : Statement() {
            override fun evaluate() {
                val oldSemanticsProvided = KakaoCompose.Global.semanticsProvider
                KakaoCompose.Global.semanticsProvider = semanticsProvider
                base.evaluate()
                KakaoCompose.Global.semanticsProvider = oldSemanticsProvided
            }
        }
}
