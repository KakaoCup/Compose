package io.github.kakaocup.compose.test

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import io.github.kakaocup.compose.rule.KakaoComposeTestRule
import io.github.kakaocup.compose.sample.DialogActivity
import io.github.kakaocup.compose.screen.DialogActivityScreen
import org.junit.Rule
import org.junit.Test

class DialogTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<DialogActivity>()

    @get:Rule
    val kakaoComposeTestRule = KakaoComposeTestRule(
        semanticsProvider = composeTestRule,
        useUnmergedTree = true
    )

    @Test
    fun openAndDismissAlertDialogTest() {
        onComposeScreen<DialogActivityScreen> {
            status.assertTextEquals("No Status")
            alertDialog.assertIsNotDisplayed()

            showAlertDialogButton.performClick()
            status.assertTextEquals("Dialog Opened")
            alertDialog.assertIsDisplayed()

            dismissAlertDialogButton.performClick()
            status.assertTextEquals("Dialog Dismissed")
            alertDialog.assertIsNotDisplayed()
        }
    }
}
