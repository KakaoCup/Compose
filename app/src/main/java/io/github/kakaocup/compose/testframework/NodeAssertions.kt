package io.github.kakaocup.compose.testframework

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed

interface NodeAssertions {
    var nodeInteraction: SemanticsNodeInteraction?

    fun isDisplayed(){
        nodeInteraction?.assertIsDisplayed() ?: throw Exception("View not matched")
    }
}