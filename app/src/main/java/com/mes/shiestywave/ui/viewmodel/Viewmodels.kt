package com.mes.shiestywave.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mes.shiestywave.data.data.local.entity.Artist
import com.mes.shiestywave.data.data.local.entity.FeaturedArtist
import com.mes.shiestywave.data.data.local.entity.Song
import com.mes.shiestywave.domain.usecase.ArtistUseCase
import com.mes.shiestywave.domain.usecase.SongUseCase
import com.mes.shiestywave.utils.safeLaunchWithIo
import kotlinx.coroutines.flow.first
import org.joda.time.DateTime

class SongViewModel(
    private val songUseCase: SongUseCase
) : ViewModel() {

    fun getSongs() = songUseCase.allSongs()
    suspend fun getSong(id: String) = songUseCase.get(id = id)
}

class ArtistViewModel(
    private val artistUseCase: ArtistUseCase
) : ViewModel() {
    fun getArtists() = artistUseCase.allArtists()
    suspend fun getArtist(id: String) = artistUseCase.get(id)
}

class HomeViewModel(
    private val songUseCase: SongUseCase,
    private val artistUseCase: ArtistUseCase,
) : ViewModel() {

    init {
        val featuredArtists = mutableListOf<FeaturedArtist>()
        val artists = mutableListOf<Artist>()
        val songs = mutableListOf<Song>()
        viewModelScope.safeLaunchWithIo {
            songUseCase.nukeDb()

            artists.addAll(
                listOf(
                    Artist("Drake"),
                    Artist("Travis Scott"),
                    Artist("A\$ap Rocky"),
                    Artist("The Midnight"),
                    Artist("Gucci Mane"),
                    Artist("Pooh Shiesty"),
                    Artist("Big 30"),
                    Artist("Lil Durk"),
                    Artist("Lil Baby"),
                    Artist("Migos"),
                )
            )
            songs.addAll(
                listOf(
                    Song(
                        "SICKO MODE", artists[1].id,
                        "https://media.pitchfork.com/photos/5b649fcc0b743703a498201f/1:1/w_500/Travis%20Scott%20_%20Astroworld_Cover%20Art.jpg",
                        "https://youtu.be/6ONRf7h3Mdk",
                        DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[0].id),
                            )
                        )
                    },
                    Song(
                        "I'm Upset!", artists[0].id,
                        "https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fbryanrolli%2Ffiles%2F2018%2F06%2Fdrake_im_upset.jpg",
                        "https://youtu.be/rIhx2wZ8jnA", DateTime.now()
                    ),
                    Song(
                        "Fashion Killa", artists[2].id,
                        "https://assets.teenvogue.com/photos/55832201c3f29bdf1f2b631d/16:9/w_2560%2Cc_limit/entertainment-music-2013-09-rihanna-asap-rocky-fashion-killa-main.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ),
                    Song(
                        "Gloria", artists[3].id,
                        "https://f4.bcbits.com/img/a1146583911_10.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ),
                    Song(
                        "Shit Crazy", artists[4].id,
                        "https://linkstorage.linkfire.com/medialinks/images/6d4a51e9-418c-4551-a760-98ac85cad67f/artwork-440x440.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[6].id),
                            )
                        )
                    },
                    Song(
                        "Back In Blood", artists[5].id,
                        "https://static.hiphopdx.com/2021/02/Screen-Shot-2021-02-05-at-12.30.19-PM-e1612546278629.png",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[7].id),
                            )
                        )
                    },
                    Song(
                        "Allegations", artists[6].id,
                        "https://images.genius.com/989f620dc5adbb16348fb23f496901c0.1000x999x1.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[5].id),
                            )
                        )
                    },
                    Song(
                        "Finesse Out The Gang Way", artists[7].id,
                        "https://i.ytimg.com/vi/m2x-r1Cyrt0/maxresdefault.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[8].id),
                            )
                        )
                    },
                    Song(
                        "Yes Indeed", artists[8].id,
                        "https://static.hiphopdx.com/2018/05/Drake-IG-827x620.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[0].id),
                            )
                        )
                    },
                    Song(
                        "Walk It Like I Talk It", artists[9].id,
                        "https://thesource.com/wp-content/uploads/2018/03/Walk-It-Talk-It-Video-Migos-Drake.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[0].id),
                            )
                        )
                    },
                )
            )

            songUseCase.save(songs).first()
            artistUseCase.save(artists).first()
            artistUseCase.saveFeaturedArtists(featuredArtists).first()
        }
    }

    fun getArtists() = artistUseCase.allArtists()
    suspend fun getArtist(id: String) = artistUseCase.get(id)

    fun getSongs() = songUseCase.allSongs()
    suspend fun getSong(id: String) = songUseCase.get(id = id)
}
