package io.github.kakaocup.compose

import androidx.compose.ui.test.junit4.createComposeRule
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import org.junit.Rule

abstract class SampleNoActivityTest {

    init {
        KakaoCompose.Override.useUnmergedTree = true
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(composeTestRule)

}
