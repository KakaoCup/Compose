package io.github.kakaocup.compose.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kakaocup.compose.sample.semantics.backgroundColorSemantic
import io.github.kakaocup.compose.sample.semantics.backgroundShapeSemantic
import io.github.kakaocup.compose.sample.semantics.imageContentSemantic
import io.github.kakaocup.compose.sample.semantics.textColorSemantic
import io.github.kakaocup.compose.sample.semantics.tintColorSemantic

@Composable
fun MainScreen() {
    var tintColor by remember { mutableStateOf(Color.Black) }
    var textColor by remember { mutableStateOf(Color.Black) }
    var backgroundColor by remember { mutableStateOf(Color.Black) }
    var backgroundShape by remember { mutableStateOf(RectangleShape) }
    var iconRes by remember { mutableIntStateOf(R.drawable.ic_android) }
    var iconImageVector by remember { mutableStateOf(Icons.Filled.AccountCircle) }

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

        Row {
            Icon(
                modifier = Modifier
                    .imageContentSemantic(iconRes)
                    .tintColorSemantic(tintColor)
                    .testTag("iconDrawableRes"),
                painter = painterResource(id = iconRes),
                tint = tintColor,
                contentDescription = null
            )
            Icon(
                modifier = Modifier
                    .imageContentSemantic(iconImageVector)
                    .tintColorSemantic(tintColor)
                    .testTag("iconImageVector"),
                imageVector = iconImageVector,
                tint = tintColor,
                contentDescription = null
            )
        }

        Button(
            content = {
                Text(text = stringResource(R.string.button_change_icon))
            },
            onClick = {
                tintColor = Color.Blue
                iconRes = R.drawable.ic_adb
                iconImageVector = Icons.Filled.Call
            },
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "changeIconButton" }
        )

        Text(
            text = "Text without style",
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "textWithoutStyle" }
        )

        Text(
            text = "Text with style",
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "textWithStyle" }
                .textColorSemantic(textColor),
            style = MaterialTheme.typography.body1.copy(
                color = textColor
            )
        )

        Text(
            text = "Text with semantic property",
            color = textColor,
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "textWithSemantic" }
                .textColorSemantic(textColor)
        )

        Button(
            content = {
                Text(text = stringResource(R.string.button_change_text_color))
            },
            onClick = {
                textColor = Color.Blue
            },
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "changeTextColorButton" }
        )

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(backgroundColor, backgroundShape)
                .backgroundColorSemantic(backgroundColor)
                .backgroundShapeSemantic(backgroundShape)
                .testTag("backgroundColorAndShapeBox")
        )

        Button(
            content = {
                Text(text = stringResource(R.string.button_change_background_color_and_shape))
            },
            onClick = {
                backgroundColor = Color.Blue
                backgroundShape = RoundedCornerShape(20.dp)
            },
            modifier = Modifier
                .padding(8.dp)
                .semantics { testTag = "backgroundColorAndShapeButton" }
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
