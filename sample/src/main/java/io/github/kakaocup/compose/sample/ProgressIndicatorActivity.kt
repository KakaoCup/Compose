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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kakaocup.compose.foundation.CircularProgressIndicator
import io.github.kakaocup.compose.foundation.LinearProgressIndicator
import io.github.kakaocup.compose.sample.ProgressIndicatorActivity.TextScreenTestTags.CircularWithProgress
import io.github.kakaocup.compose.sample.ProgressIndicatorActivity.TextScreenTestTags.CircularWithoutProgress
import io.github.kakaocup.compose.sample.ProgressIndicatorActivity.TextScreenTestTags.LinearWithProgress
import io.github.kakaocup.compose.sample.ProgressIndicatorActivity.TextScreenTestTags.LinearWithoutProgress
import io.github.kakaocup.compose.sample.ProgressIndicatorActivity.TextScreenTestTags.ProgressIndicatorScreen


class ProgressIndicatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }

    object TextScreenTestTags {
        const val ProgressIndicatorScreen = "Screen"
        const val CircularWithProgress = "CircularWithProgress"
        const val CircularWithoutProgress = "CircularWithoutProgress"
        const val LinearWithProgress = "LinearWithProgress"
        const val LinearWithoutProgress = "LinearWithoutProgress"
    }

    @Composable
    private fun Screen() {
        Column(
            modifier = Modifier
                .semantics { testTag = ProgressIndicatorScreen }
        ) {
            LinearProgressIndicator(
                progress = 0.3f,
                color = Color.Green,
                strokeCap = StrokeCap.Round,
                backgroundColor = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .semantics { testTag = LinearWithProgress }
            )
            LinearProgressIndicator(
                color = Color.Green,
                strokeCap = StrokeCap.Round,
                backgroundColor = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .semantics { testTag = LinearWithoutProgress }
            )
            CircularProgressIndicator(
                progress = 0.7f,
                color = Color.Green,
                strokeCap = StrokeCap.Round,
                strokeWidth = 4.dp,
                backgroundColor = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .semantics { testTag = CircularWithProgress }
            )
            CircularProgressIndicator(
                color = Color.Green,
                strokeCap = StrokeCap.Round,
                strokeWidth = 4.dp,
                backgroundColor = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .semantics { testTag = CircularWithoutProgress }
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
