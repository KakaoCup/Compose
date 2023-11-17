package io.github.kakaocup.compose.sample

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun SimpleNavigationScreen() {
    var currentScreen by rememberSaveable { mutableStateOf(NavigationScreen.First) }

    when (currentScreen) {
        NavigationScreen.First -> FirstNavigationScreen(
            onNavigateSecondScreen = {
                currentScreen = NavigationScreen.Second
            }
        )
        NavigationScreen.Second -> SecondNavigationScreen()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun FirstNavigationScreen(onNavigateSecondScreen: () -> Unit) {
    Scaffold(Modifier.testTag("FirstNavigationScreen")) { _ ->
        Card(Modifier.padding(64.dp)) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                SharedWidget(
                    title = "First screen",
                    modifier = Modifier.align(Alignment.TopCenter)
                )
                Button(
                    onClick = onNavigateSecondScreen,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .testTag("NavigateSecondScreenButton")
                ) {
                    Text("Navigate second screen")
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun SecondNavigationScreen() {
    Scaffold(Modifier.testTag("SecondNavigationScreen")) { _ ->
        Card(Modifier.padding(64.dp)) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                SharedWidget(
                    title = "Second screen",
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}

@Composable
private fun SharedWidget(title: String, modifier: Modifier = Modifier) {
    Box(
        modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Green)
            .testTag("SharedWidget")
    ) {
        Text(
            title,
            Modifier
                .padding(16.dp)
                .testTag("SharedWidgetTitle")
        )
    }
}

private enum class NavigationScreen {
    First,
    Second
}
