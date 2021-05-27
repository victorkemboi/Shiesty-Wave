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
        viewModelScope.safeLaunchWithIo {
            songUseCase.nukeDb()
            val featuredArtists = mutableListOf<FeaturedArtist>()
            val artists = mutableListOf<Artist>().apply {
                addAll(
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
            }
            val songs = mutableListOf<Song>().apply {
                addAll(
                    listOf(
                        Song("SICKO MODE", artists[1].name, DateTime.now()).apply {
                            featuredArtists.addAll(
                                listOf(
                                    FeaturedArtist(this.id, artists[0].id),
                                )
                            )
                        },
                        Song("I'm Upset!", artists[0].name, DateTime.now()),
                        Song("Fashion Killa", artists[2].name, DateTime.now()),
                        Song("Gloria", artists[3].name, DateTime.now()),
                        Song("Shit Crazy", artists[4].name, DateTime.now()).apply {
                            featuredArtists.addAll(
                                listOf(
                                    FeaturedArtist(this.id, artists[6].id),
                                )
                            )
                        },
                        Song("Back In Blood", artists[5].name, DateTime.now()).apply {
                            featuredArtists.addAll(
                                listOf(
                                    FeaturedArtist(this.id, artists[7].id),
                                )
                            )
                        },
                        Song("Allegations", artists[6].name, DateTime.now()).apply {
                            featuredArtists.addAll(
                                listOf(
                                    FeaturedArtist(this.id, artists[5].id),
                                )
                            )
                        },
                        Song("Finesse Out The Gang Way", artists[7].name, DateTime.now()).apply {
                            featuredArtists.addAll(
                                listOf(
                                    FeaturedArtist(this.id, artists[8].id),
                                )
                            )
                        },
                        Song("Yes Indeed", artists[8].name, DateTime.now()).apply {
                            featuredArtists.addAll(
                                listOf(
                                    FeaturedArtist(this.id, artists[0].id),
                                )
                            )
                        },
                        Song("Walk It Like I Talk It", artists[9].name, DateTime.now()).apply {
                            featuredArtists.addAll(
                                listOf(
                                    FeaturedArtist(this.id, artists[0].id),
                                )
                            )
                        },
                    )
                )
            }

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
