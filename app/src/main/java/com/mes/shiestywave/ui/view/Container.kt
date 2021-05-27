package com.mes.shiestywave.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import com.mes.shiestywave.ShiestyWaveApp
import com.mes.shiestywave.data.data.local.entity.Song
import com.mes.shiestywave.ui.theme.Red500
import com.mes.shiestywave.ui.theme.ShiestyWaveTheme
import com.mes.shiestywave.ui.theme.Teal200

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
@Preview
fun LoginScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Card(
            backgroundColor = Teal200,
            shape = RoundedCornerShape(3.dp),
            elevation = 8.dp,
            modifier = Modifier
                .clickable(
                    onClick = {
                        // this will navigate to second screen
                        // navController.navigate("second_screen")

                        Toast
                            .makeText(
                                context,
                                "Diamonds Dancing",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                )
        ) {
            Text(
                text = "YSL ft Travis Scott",
                color = Color.White,
                style = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier.padding(12.dp)
            )
        }

        Card(
            backgroundColor = Red500,
            shape = RoundedCornerShape(3.dp),
            elevation = 8.dp,
            modifier = Modifier
                .clickable(
                    onClick = {
                        // this will navigate to second screen
                        // navController.navigate("second_screen")

                        Toast
                            .makeText(
                                context,
                                "Shit Crazy",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                )
        ) {
            Text(
                text = "Gucci ft Big30",
                color = Color.White,
                style = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Composable
fun Song(song: Song, featuredArtists: List<>) {

}


