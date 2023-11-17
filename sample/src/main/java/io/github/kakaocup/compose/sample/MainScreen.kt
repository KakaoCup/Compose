package io.github.kakaocup.compose.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .semantics { testTag = "MainScreen" }
    ) {
        Text(
            text = "Simple text 1",
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "mySimpleText" }
        )

        Text(
            text = "Simple text 2",
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "mySimpleText" }
        )

        Button(
            content = {
                Text(text = stringResource(R.string.button_1))
            },
            onClick = {
                // nothing
            },
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "myTestButton" }
        )

        var count by remember { mutableStateOf(0) }
        Button(
            content = {
                Text(text = "$count")
            },
            onClick = {
                count += 1
            },
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "clickCounter" }
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun MainScreenPreview() {
    MaterialTheme {
        MainScreen()
    }
}