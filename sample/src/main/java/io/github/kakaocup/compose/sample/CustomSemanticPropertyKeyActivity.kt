package io.github.kakaocup.compose.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val CustomSemanticKey = SemanticsPropertyKey<Any>("CustomSemanticKey")
var SemanticsPropertyReceiver.customSemanticKey by CustomSemanticKey

object CustomSemanticsScreenTestTags {
    const val Screen = "Screen"
    const val view = "view"
}

class CustomSemanticPropertyKeyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }
}

@Composable
private fun Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .semantics { testTag = "Screen" }
    ) {
        Text(
            text = "Simple text 1",
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "view" }
                .semantics { customSemanticKey = "Any value" }
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun Preview() {
    MaterialTheme {
        Screen()
    }
}
