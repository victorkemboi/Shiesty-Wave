package com.mes.shiestywave.ui.view

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mes.shiestywave.R
import com.mes.shiestywave.ShiestyWaveApp
import com.mes.shiestywave.domain.model.SongUiModel
import com.mes.shiestywave.ui.theme.Pink700
import com.mes.shiestywave.ui.theme.Pink900
import com.mes.shiestywave.ui.viewmodel.HomeViewModel
import com.mes.shiestywave.utils.getCharacterBackground
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.* // ktlint-disable no-wildcard-imports

class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModel()
    @InternalCoroutinesApi
    @ExperimentalComposeApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShiestyWaveApp(homeViewModel)
        }
    }
}

@InternalCoroutinesApi
@ExperimentalComposeApi
@Composable
fun SongScreen(homeViewModel: HomeViewModel, navController: NavHostController) {
    ConstraintLayout {
        val (viewPort, fabLayout) = createRefs()
        Column(
            modifier = Modifier.constrainAs(viewPort) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        ) {
            Row {
                Icon(
                    painter = painterResource(R.drawable.ic_music_library),
                    contentDescription = "print",
                    tint = Pink700,
                    modifier = Modifier
                        .alignByBaseline()
                        .padding(8.dp)
                        .padding(
                            start = 12.dp
                        )
                )
                Text(
                    color = Pink700,
                    style = TextStyle(textAlign = TextAlign.Start),
                    modifier = Modifier
                        .padding(8.dp)
                        .alignByBaseline(),
                    fontWeight = FontWeight.Medium,
                    text = "Top heat songs",
                    fontSize = 20.sp
                )
            }

            Divider(color = Color.LightGray, thickness = 1.dp)
            Songs(songs = homeViewModel.getSongs())
        }

        Row(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth(fraction = 1F)
                .constrainAs(fabLayout) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Surface(
                modifier = Modifier
                    .size(50.dp)
                    .padding(12.dp),
                shape = CircleShape,
                color = Color.Black
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_music_library),
                    contentDescription = "Songs",
                    tint = Pink700,
                    modifier = Modifier.size(30.dp)
                        .alignByBaseline()
                        .padding(8.dp)
                )
            }

            Surface(
                modifier = Modifier
                    .size(50.dp)
                    .padding(12.dp)
                    .clickable {
                        navController.navigate("artists") {
                            launchSingleTop = true
                        }
                    },
                shape = CircleShape,
                color = Color.Black
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_account_box),
                    contentDescription = "artists",
                    tint = Color.LightGray,
                    modifier = Modifier.size(30.dp)
                        .alignByBaseline()
                        .padding(8.dp)
                )
            }
        }
    }
}

@InternalCoroutinesApi
@Composable
fun Songs(songs: Flow<PagingData<SongUiModel.SongModel>>) {
    val lazySongs = songs.collectAsLazyPagingItems()
    SwipeRefresh(
        state = rememberSwipeRefreshState(
            lazySongs.loadState.append == LoadState.Loading
        ),
        onRefresh = { lazySongs.refresh() },
    ) {
        LazyColumn(
            modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Pink900,
                        Color.DarkGray
                    )
                )
            )
        ) {
            itemsIndexed(lazySongs) { index, item ->
                if (item != null) {
                    Song(song = item, index = index + 1)
                }
            }
        }
    }
}

@InternalCoroutinesApi
@Composable
fun Song(song: SongUiModel.SongModel, index: Int) {
    val context = LocalContext.current
    Card(
        backgroundColor = getCharacterBackground(
            song.song.name.first().toString().capitalize(Locale.ROOT)
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier
            .clickable(
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(song.song.youtubeUri)
                    )
                    intent.component = ComponentName(
                        "com.google.android.youtube",
                        "com.google.android.youtube.PlayerActivity"
                    )

                    try {
                        startActivity(context, intent, null)
                    } catch (ex: ActivityNotFoundException) {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(song.song.youtubeUri)
                            )
                        )
                    }
                }
            )
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = rememberGlidePainter(
                    request = song.song.coverArt
                ),
                contentDescription = song.song.name,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Row {
                Text(
                    color = Color.White,
                    style = TextStyle(textAlign = TextAlign.Start),
                    modifier = Modifier.padding(12.dp),
                    text = "$index ."
                )
                Text(
                    color = Color.White,
                    style = TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier.padding(12.dp),
                    text = song.title
                )
            }
        }
    }
}
