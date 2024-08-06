package io.github.kakaocup.compose.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kakaocup.compose.sample.SemanticsScreenTestTags.BackgroundBrushAndShapeBox
import io.github.kakaocup.compose.sample.SemanticsScreenTestTags.BackgroundColorAndShapeBox
import io.github.kakaocup.compose.sample.SemanticsScreenTestTags.SemanticsScreeen
import io.github.kakaocup.compose.semantics.background

class BackgroundSemanticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BackgroundSemanticsScreen()
        }
    }
}

object SemanticsScreenTestTags {
    const val SemanticsScreeen = "SemanticsScreeen"
    const val BackgroundColorAndShapeBox = "backgroundColorAndShapeBox"
    const val BackgroundBrushAndShapeBox = "backgroundBrushAndShapeBox"
}

@Composable
fun BackgroundSemanticsScreen() {

    var backgroundColor by remember { mutableStateOf(Color.Black) }
    var backgroundShape by remember { mutableStateOf(RectangleShape) }
    var backgroundBrush by remember { mutableStateOf(Brush.linearGradient(listOf(Color.Green, Color.Red))) }

    Column(
        modifier = Modifier
            .semantics { testTag = SemanticsScreeen }
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(backgroundColor, backgroundShape)
                .testTag(BackgroundColorAndShapeBox)
        )

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(backgroundBrush, backgroundShape, 0.5f)
                .testTag(BackgroundBrushAndShapeBox)
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun SemanticsScreenPreview() {
    MaterialTheme {
        BackgroundSemanticsScreen()
    }
}
