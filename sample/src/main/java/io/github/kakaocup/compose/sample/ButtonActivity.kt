package io.github.kakaocup.compose.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kakaocup.compose.foundation.Button
import io.github.kakaocup.compose.foundation.OutlinedButton
import io.github.kakaocup.compose.foundation.Text
import io.github.kakaocup.compose.foundation.TextButton

class ButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }

    object ButtonScreenTestTags {
        const val ButtonScreen = "Screen"
        const val Button = "Button"
        const val OutlinedButton = "OutlinedButton"
        const val TextButton = "TextButton"

    }

    @Composable
    private fun Screen() {
        Column(
            modifier = Modifier
                .semantics { testTag = ButtonScreenTestTags.ButtonScreen }
        ) {
            Button(
                onClick = {},
                modifier = Modifier.semantics {
                    testTag = ButtonScreenTestTags.Button
                },
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(12.dp, Color.Green),
                contentPadding = PaddingValues(
                    start = 32.dp,
                    end = 32.dp,
                    top = 32.dp,
                    bottom = 32.dp
                )
            ) {
                Text(text = "Button 1")
            }

            OutlinedButton(
                onClick = {},
                modifier = Modifier.semantics {
                    testTag = ButtonScreenTestTags.OutlinedButton
                },
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(12.dp, Color.Green),
                contentPadding = PaddingValues(
                    start = 32.dp,
                    end = 32.dp,
                    top = 32.dp,
                    bottom = 32.dp
                )
            ) {
                Text(text = "Button 1")
            }

            TextButton(
                onClick = {},
                modifier = Modifier.semantics {
                    testTag = ButtonScreenTestTags.TextButton
                },
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(12.dp, Color.Green),
                contentPadding = PaddingValues(
                    start = 32.dp,
                    end = 32.dp,
                    top = 32.dp,
                    bottom = 32.dp
                )
            ) {
                Text(text = "Button 1")
            }
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
