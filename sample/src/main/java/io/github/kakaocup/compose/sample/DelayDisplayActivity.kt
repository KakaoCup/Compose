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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DelayDisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isVisible = remember { mutableStateOf(false) }

            CoroutineScope(Dispatchers.Main).launch {
                CoroutineScope(Dispatchers.Default).async {
                    delay(500)
                }.await()

                isVisible.value = true
            }

            Screen(isVisible.value)
        }
    }
}

object DelayDisplayActivityTestTags {
    const val Screen = "Screen"
    const val mySimpleText = "mySimpleText"
}

@Composable
private fun Screen(visible: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .semantics { testTag = "Screen" }
    ) {
        if (visible) {
            Text(
                text = "Simple text 1",
                modifier = Modifier
                    .padding(8.dp)
                    .semantics { testTag = "mySimpleText" }
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun ScreenPreview() {
    MaterialTheme {
        Screen(true)
    }
}
