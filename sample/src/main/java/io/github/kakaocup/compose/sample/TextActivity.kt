package io.github.kakaocup.compose.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kakaocup.compose.foundation.Text
import io.github.kakaocup.compose.sample.TextActivity.TextScreenTestTags.textWithSemantic
import io.github.kakaocup.compose.sample.TextActivity.TextScreenTestTags.textWithStyle
import io.github.kakaocup.compose.sample.TextActivity.TextScreenTestTags.textWithoutStyle

class TextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextScreen()
        }
    }

    object TextScreenTestTags {
        const val TextScreen = "Screen"
        const val textWithoutStyle = "textWithoutStyle"
        const val textWithStyle = "textWithStyle"
        const val textWithSemantic = "textWithSemantic"
    }

    @Composable
    fun TextScreen() {
        Column(
            modifier = Modifier
                .semantics { testTag = TextScreenTestTags.TextScreen }
        ) {
            Text(
                text = "Text without style",
                modifier = Modifier
                    .padding(8.dp)
                    .semantics { testTag = textWithoutStyle }
            )

            Text(
                text = "Text with style",
                modifier = Modifier
                    .padding(8.dp)
                    .semantics { testTag = textWithStyle },
                style = MaterialTheme.typography.body1.copy(
                    color = Color.Black
                )
            )

            Text(
                text = "Text with semantic property",
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
                    .semantics { testTag = textWithSemantic }
            )
        }
    }

    @Composable
    @Preview(showSystemUi = true)
    private fun TextScreenScreenPreview() {
        MaterialTheme {
            TextScreen()
        }
    }
}
