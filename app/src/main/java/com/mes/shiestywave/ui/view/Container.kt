package com.mes.shiestywave.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mes.shiestywave.R
import com.mes.shiestywave.ShiestyWaveApp
import com.mes.shiestywave.domain.model.SongUiModel
import com.mes.shiestywave.ui.theme.ShiestyWaveTheme
import com.mes.shiestywave.ui.viewmodel.HomeViewModel
import com.mes.shiestywave.utils.getCharacterBackground
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.* // ktlint-disable no-wildcard-imports

class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModel()
    @ExperimentalComposeApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShiestyWaveApp(homeViewModel)
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

@ExperimentalComposeApi
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    Column {
        Row {
            Icon(
                painter = painterResource(R.drawable.ic_music_library),
                contentDescription = "print",
                tint = Color.DarkGray,
                modifier = Modifier.alignByBaseline().padding(8.dp).padding(
                    start = 12.dp
                )
            )
            Text(
                color = Color.DarkGray,
                style = TextStyle(textAlign = TextAlign.Start),
                modifier = Modifier.padding(8.dp).alignByBaseline(),
                fontWeight = FontWeight.Medium,
                text = "Top heat songs",
                fontSize = 20.sp
            )
        }

        Divider(color = Color.LightGray, thickness = 1.dp)
        Songs(songs = homeViewModel.getSongs())
    }
}

@Composable
fun Songs(songs: Flow<PagingData<SongUiModel.SongModel>>) {
    val lazySongs = songs.collectAsLazyPagingItems()
    SwipeRefresh(
        state = rememberSwipeRefreshState(
            lazySongs.loadState.append == LoadState.Loading
        ),
        onRefresh = { lazySongs.refresh() },
    ) {
        LazyColumn {
            itemsIndexed(lazySongs) { index, item ->
                if (item != null) {
                    Song(song = item, index = index + 1)
                }
            }
        }
    }
}

@Composable
fun Song(song: SongUiModel.SongModel, index: Int) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.padding(
            16.dp
        ),
    ) {
        Text(
            color = Color.DarkGray,
            style = TextStyle(textAlign = TextAlign.Start),
            modifier = Modifier.padding(12.dp),
            text = "$index ."
        )
        Card(
            backgroundColor = getCharacterBackground(
                song.song.name.first().toString().capitalize(Locale.ROOT)
            ),
            shape = RoundedCornerShape(3.dp),
            elevation = 8.dp,
            modifier = Modifier
                .clickable(
                    onClick = {
                        Toast
                            .makeText(
                                context,
                                "I am a song!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                )
        ) {

            Text(
                color = Color.White,
                style = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier.padding(12.dp),
                text = song.title
            )
        }
    }
}
