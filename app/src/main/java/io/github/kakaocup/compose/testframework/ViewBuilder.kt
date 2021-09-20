package io.github.kakaocup.compose.testframework

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText

class ViewBuilder(private val composeTestRule: AndroidComposeTestRule<*,*>) {

    var nodeInteraction : SemanticsNodeInteraction? = null

    fun withTag(tag: String){
        nodeInteraction = composeTestRule.onNodeWithTag(tag)
    }

}