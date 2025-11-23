package io.github.kakaocup.compose.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }

    object TestTags {
        const val Screen = "Screen"
        const val StatusText = "StatusText"
        const val ShowAlertDialogButton = "ShowAlertDiaglog"
        const val AlertDialog = "AlertDialog"
        const val AlertDialogDismissButton = "AlertDialogDismissButton"
    }

    @Composable
    private fun Screen() {

        val openAlertDialog = remember { mutableStateOf(false) }
        val status = remember { mutableStateOf("No Status") }

        when {
            openAlertDialog.value -> {
                AlertDialogExample(
                    onDismissRequest = {
                        status.value = "Dialog Dismissed"
                        openAlertDialog.value = false
                    },
                    onConfirmation = {
                        status.value = "Dialog Confirmed"
                        openAlertDialog.value = false
                    },
                    dialogTitle = "Alert dialog example",
                    dialogText = "This is an example of an alert dialog with buttons.",
                )
            }
        }

        Column(
            modifier = Modifier
                .semantics { testTag = TestTags.Screen }
        ) {
            Text(
                text = status.value,
                modifier = Modifier
                    .padding(8.dp)
                    .semantics { testTag = TestTags.StatusText }
            )

            Button(
                content = {
                    Text(text = "Show Alert Dialog")
                },
                onClick = {
                    status.value = "Dialog Opened"
                    openAlertDialog.value = true
                },
                modifier = Modifier
                    .padding(8.dp)
                    .semantics { testTag = TestTags.ShowAlertDialogButton }
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

    @Composable
    fun AlertDialogExample(
        onDismissRequest: () -> Unit,
        onConfirmation: () -> Unit,
        dialogTitle: String,
        dialogText: String,
    ) {
        AlertDialog(
            modifier = Modifier.testTag(TestTags.AlertDialog),
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(text = dialogText)
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    modifier = Modifier.testTag(TestTags.AlertDialogDismissButton),
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}
