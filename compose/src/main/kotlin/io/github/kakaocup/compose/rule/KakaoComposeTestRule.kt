package io.github.kakaocup.compose.rule

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.KakaoCompose
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class KakaoComposeTestRule(
    val semanticsProvider: SemanticsNodeInteractionsProvider? = null,
    val useUnmergedTree: Boolean? = null
) : TestRule {
    override fun apply(base: Statement, description: Description): Statement =
        object : Statement() {
            override fun evaluate() {
                val preEvaluatedSemanticsProvided = KakaoCompose.Global.semanticsProvider
                semanticsProvider?.let { KakaoCompose.Global.semanticsProvider = it }
                val preUseUnmergedTree = KakaoCompose.Override.useUnmergedTree
                useUnmergedTree?.let { KakaoCompose.Override.useUnmergedTree = it }

                base.evaluate()

                KakaoCompose.Global.semanticsProvider = preEvaluatedSemanticsProvided
                KakaoCompose.Override.useUnmergedTree = preUseUnmergedTree
            }
        }
}

