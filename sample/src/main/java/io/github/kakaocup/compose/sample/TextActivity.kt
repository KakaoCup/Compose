package io.github.kakaocup.compose.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.kakaocup.compose.foundation.Text
import io.github.kakaocup.compose.sample.TextActivity.TextScreenTestTags.textWithAllFields
import io.github.kakaocup.compose.sample.TextActivity.TextScreenTestTags.textWithSemantic
import io.github.kakaocup.compose.sample.TextActivity.TextScreenTestTags.textWithStyle
import io.github.kakaocup.compose.sample.TextActivity.TextScreenTestTags.textWithoutStyle

class TextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }

    object TextScreenTestTags {
        const val TextScreen = "Screen"
        const val textWithAllFields = "textWithoutStyle"
        const val textWithoutStyle = "textWithoutStyle"
        const val textWithStyle = "textWithStyle"
        const val textWithSemantic = "textWithSemantic"

    }

    @Composable
    private fun Screen() {
        Column(
            modifier = Modifier
                .semantics { testTag = TextScreenTestTags.TextScreen }
        ) {
            Text(
                text = "Text With All Fields",
                color = Color.Black,
                fontSize = 16.sp,
                fontStyle = Italic,
                fontWeight = FontWeight.Bold,
                letterSpacing = 4.sp,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Left,
                lineHeight = 6.sp,
                overflow = TextOverflow.Ellipsis,
                softWrap = false,
                maxLines = 10,
                minLines = 3,
                modifier = Modifier
                    .padding(8.dp)
                    .semantics { testTag = textWithAllFields }
            )

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
            Screen()
        }
    }
}
