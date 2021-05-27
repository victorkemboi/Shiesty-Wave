package com.mes.shiestywave.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mes.shiestywave.R
import com.mes.shiestywave.domain.model.ArtistSongsUiModel
import com.mes.shiestywave.ui.theme.* // ktlint-disable no-wildcard-imports
import com.mes.shiestywave.ui.viewmodel.HomeViewModel
import com.mes.shiestywave.utils.getCharacterBackground
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import java.util.* // ktlint-disable no-wildcard-imports

@InternalCoroutinesApi
@ExperimentalComposeApi
@Composable
fun ArtistScreen(homeViewModel: HomeViewModel, navController: NavHostController) {
    Column {
        Row {
            Icon(
                painter = painterResource(R.drawable.ic_account_box),
                contentDescription = "print",
                tint = Group2,
                modifier = Modifier
                    .alignByBaseline()
                    .padding(8.dp)
                    .padding(
                        start = 12.dp
                    )
            )
            Text(
                color = Teal700,
                style = TextStyle(textAlign = TextAlign.Start),
                modifier = Modifier
                    .padding(8.dp)
                    .alignByBaseline(),
                fontWeight = FontWeight.Medium,
                text = "Artists",
                fontSize = 20.sp
            )
        }

        Divider(color = Color.LightGray, thickness = 1.dp)
        Artists(artists = homeViewModel.getArtists())
    }

    Row {
        FloatingActionButton(
            onClick = {
                navController.navigate("songs") {
                    launchSingleTop = true
                }
            },
            modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        Color.Black
                    )
                )
            ).padding(12.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_music_library),
                contentDescription = "Songs",
                tint = Pink700,
                modifier = Modifier
                    .alignByBaseline()
                    .padding(8.dp)
                    .padding(
                        start = 12.dp
                    )
            )
        }

        FloatingActionButton(
            onClick = {
            },
            modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        Color.Black
                    )
                )
            ).padding(12.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_account_box),
                contentDescription = "artists",
                tint = Teal700,
                modifier = Modifier
                    .alignByBaseline()
                    .padding(8.dp)
                    .padding(
                        start = 12.dp
                    )
            )
        }
    }
}

@InternalCoroutinesApi
@Composable
fun Artists(artists: Flow<PagingData<ArtistSongsUiModel.ArtistSongsModel>>) {
    val lazyArtists = artists.collectAsLazyPagingItems()
    SwipeRefresh(
        state = rememberSwipeRefreshState(
            lazyArtists.loadState.append == LoadState.Loading
        ),
        onRefresh = { lazyArtists.refresh() },
    ) {
        LazyColumn(
            modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Teal900,
                        Color.DarkGray
                    )
                )
            )
        ) {
            itemsIndexed(lazyArtists) { index, item ->
                if (item != null) {
                    Artist(artist = item, index = index + 1)
                }
            }
        }
    }
}

@InternalCoroutinesApi
@Composable
fun Artist(artist: ArtistSongsUiModel.ArtistSongsModel, index: Int) {
    val context = LocalContext.current
    Card(
        backgroundColor = getCharacterBackground(
            artist.artist.artist.name.first().toString().capitalize(Locale.ROOT)
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier
            .clickable(
                onClick = {}
            )
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            val coverUri: String = try { artist.artCovers.first() } catch (e: NoSuchElementException) { "" }
            Image(
                painter = rememberGlidePainter(
                    request = coverUri
                ),
                contentDescription = artist.artist.artist.name,
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
                    text = artist.artist.artist.name
                )
            }
        }
    }
}
