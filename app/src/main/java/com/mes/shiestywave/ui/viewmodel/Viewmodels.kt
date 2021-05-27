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
                        "I'm Upset!", artists[0].id, "https://media.pitchfork.com/photos/5b649fcc0b743703a498201f/1:1/w_500/Travis%20Scott%20_%20Astroworld_Cover%20Art.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ),
                    Song(
                        "Fashion Killa", artists[2].id, "https://media.pitchfork.com/photos/5b649fcc0b743703a498201f/1:1/w_500/Travis%20Scott%20_%20Astroworld_Cover%20Art.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ),
                    Song(
                        "Gloria", artists[3].id, "https://media.pitchfork.com/photos/5b649fcc0b743703a498201f/1:1/w_500/Travis%20Scott%20_%20Astroworld_Cover%20Art.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ),
                    Song(
                        "Shit Crazy", artists[4].id, "https://media.pitchfork.com/photos/5b649fcc0b743703a498201f/1:1/w_500/Travis%20Scott%20_%20Astroworld_Cover%20Art.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[6].id),
                            )
                        )
                    },
                    Song(
                        "Back In Blood", artists[5].id, "https://media.pitchfork.com/photos/5b649fcc0b743703a498201f/1:1/w_500/Travis%20Scott%20_%20Astroworld_Cover%20Art.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[7].id),
                            )
                        )
                    },
                    Song(
                        "Allegations", artists[6].id, "https://media.pitchfork.com/photos/5b649fcc0b743703a498201f/1:1/w_500/Travis%20Scott%20_%20Astroworld_Cover%20Art.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[5].id),
                            )
                        )
                    },
                    Song(
                        "Finesse Out The Gang Way", artists[7].id, "https://media.pitchfork.com/photos/5b649fcc0b743703a498201f/1:1/w_500/Travis%20Scott%20_%20Astroworld_Cover%20Art.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[8].id),
                            )
                        )
                    },
                    Song(
                        "Yes Indeed", artists[8].id, "https://media.pitchfork.com/photos/5b649fcc0b743703a498201f/1:1/w_500/Travis%20Scott%20_%20Astroworld_Cover%20Art.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[0].id),
                            )
                        )
                    },
                    Song(
                        "Walk It Like I Talk It", artists[9].id, "https://media.pitchfork.com/photos/5b649fcc0b743703a498201f/1:1/w_500/Travis%20Scott%20_%20Astroworld_Cover%20Art.jpg",
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
