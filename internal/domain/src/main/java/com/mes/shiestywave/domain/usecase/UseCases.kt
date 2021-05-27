package com.mes.shiestywave.domain.usecase

import androidx.paging.map
import com.mes.shiestywave.data.data.local.entity.Artist
import com.mes.shiestywave.data.data.local.entity.unknownArtist
import com.mes.shiestywave.data.repository.ArtistRepository
import com.mes.shiestywave.data.repository.FeaturedArtistRepository
import com.mes.shiestywave.data.repository.SongRepository
import com.mes.shiestywave.domain.model.SongUiModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SongUseCase(
    private val songRepository: SongRepository,
    private val artistRepository: ArtistRepository,
    private val featuredArtistRepository: FeaturedArtistRepository
) {
    suspend fun get(id: String) = songRepository.getSong(id = id)
    fun allSongs() = songRepository.fetchAllSongs().map {
        songs ->
        songs.map {
            song ->
            SongUiModel.SongModel(
                song = song,
                artist = artistRepository.getArtist(song.artist),
                featuredArtist = featuredArtistRepository.getFeaturedArtists(song.id).first().map {
                    artistRepository.getArtist(song.artist) ?: unknownArtist
                }
            )
        }
    }
}

class ArtistUseCase(
    private val artistRepository: ArtistRepository
) {
    fun artistSongs(artist: String) = artistRepository.fetchArtistSongs(artist = artist)
}
