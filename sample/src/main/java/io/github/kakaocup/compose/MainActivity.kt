package io.github.kakaocup.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Text(
                    text = "Simple text 1",
                    modifier = Modifier.semantics { testTag = "mySimpleText" })
                Text(
                    text = "Simple text 2",
                    modifier = Modifier.semantics { testTag = "mySimpleText" })
                Button(content = {
                    Text(text = "Button 1")
                }, onClick = {

                }, modifier = Modifier.semantics { testTag = "myTestButton" })
            }
        }
    }
}