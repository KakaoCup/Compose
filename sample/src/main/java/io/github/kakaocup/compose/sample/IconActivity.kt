package io.github.kakaocup.compose.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import io.github.kakaocup.compose.foundation.Icon

class IconActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }

    object IconScreenTestTags {
        const val IconScreen = "Screen"
        const val iconDrawableRes = "iconDrawableRes"
        const val iconImageVector = "iconImageVector"
    }

    @Composable
    private fun Screen() {
        val tintColor by remember { mutableStateOf(Color.Black) }
        val iconRes by remember { mutableIntStateOf(R.drawable.ic_android) }
        val iconImageVector by remember { mutableStateOf(Icons.Filled.AccountCircle) }

        Column(
            modifier = Modifier
                .semantics { testTag = IconScreenTestTags.IconScreen }
        ) {
            Icon(
                modifier = Modifier
                    .testTag(IconScreenTestTags.iconDrawableRes),
                resId = iconRes,
                tint = tintColor,
                contentDescription = null
            )
            Icon(
                modifier = Modifier
                    .testTag(IconScreenTestTags.iconImageVector),
                imageVector = iconImageVector,
                tint = tintColor,
                contentDescription = null
            )
        }
    }

    @Composable
    @Preview(showSystemUi = true)
    private fun MainScreenPreview() {
        MaterialTheme {
            Screen()
        }
    }
}
