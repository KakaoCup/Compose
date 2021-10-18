package io.github.kakaocup.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Text(
                    text = "Simple text 1",
                    modifier = Modifier.testTag("mySimpleText"))
                Button(content = {
                    Text(text = "Button 1")
                }, onClick = {

                }, modifier = Modifier.testTag("myTestButton"))
                OutlinedButton(content = {
                    Text(modifier = Modifier.testTag("myTestOutlinedButtonContent"), text = "Button 2")
                }, onClick = {

                }, modifier = Modifier.testTag("myTestOutlinedButton"))
            }
        }
    }
}
