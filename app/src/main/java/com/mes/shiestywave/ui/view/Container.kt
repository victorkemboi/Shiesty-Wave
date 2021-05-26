package com.mes.shiestywave.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mes.shiestywave.ShiestyWaveApp
import com.mes.shiestywave.ui.theme.ShiestyWaveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShiestyWaveApp()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShiestyWaveTheme {
        Greeting("Android")
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Say Hello!",
            color = Color.Green,
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(24.dp)
                .clickable(
                    onClick = {
                        // this will navigate to second screen
                        // navController.navigate("second_screen")

                        Toast
                            .makeText(
                                context,
                                "Hi You!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                )
        )
    }

    Text(
        text = "Not Nice",
        color = Color.Red,
        style = TextStyle(textAlign = TextAlign.Center),
        modifier = Modifier
            .padding(24.dp)
            .clickable(
                onClick = {
                    // this will navigate to second screen
                    // navController.navigate("second_screen")

                    Toast
                        .makeText(
                            context,
                            "Get Tha Hell outta here!",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
            )
    )
}
