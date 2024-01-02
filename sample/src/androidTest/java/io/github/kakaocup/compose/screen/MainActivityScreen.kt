package io.github.kakaocup.compose.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.KAppIconNode
import io.github.kakaocup.compose.node.KAppNode
import io.github.kakaocup.compose.node.KAppTextNode
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class MainActivityScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<MainActivityScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag("MainScreen") }
    ) {

    val myText1: KNode = child {
        hasTestTag("mySimpleText")
        hasPosition(0)
    }

    val myText2: KNode = child {
        hasTestTag("mySimpleText")
        hasPosition(1)
    }

    val changeIconsButton: KNode = child {
        hasTestTag("changeIconButton")
    }

    val iconDrawableRes: KAppIconNode = child {
        hasTestTag("iconDrawableRes")
    }

    val iconImageVector: KAppIconNode = child {
        hasTestTag("iconImageVector")
    }

    val changeTextColorButton: KNode = child {
        hasTestTag("changeTextColorButton")
    }

    val textWithoutStyle: KAppTextNode = child {
        hasTestTag("textWithoutStyle")
    }

    val textWithStyle: KAppTextNode = child {
        hasTestTag("textWithStyle")
    }

    val textWithSemantic: KAppTextNode = child {
        hasTestTag("textWithSemantic")
    }

    val boxColorAndShape: KAppNode = child {
        hasTestTag("backgroundColorAndShapeBox")
    }

    val changeBackgroundColorAndShapeButton: KAppNode = child {
        hasTestTag("backgroundColorAndShapeButton")
    }

    val myButton: KNode = child {
        hasTestTag("myTestButton")
        hasText("Button 1")
        hasText(io.github.kakaocup.compose.sample.R.string.button_1)
    }

    val clickCounter: KNode = child {
        hasTestTag("clickCounter")
    }
}
