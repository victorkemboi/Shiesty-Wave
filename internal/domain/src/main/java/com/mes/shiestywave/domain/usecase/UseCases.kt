package com.mes.shiestywave.domain.usecase

import androidx.paging.map
import com.mes.shiestywave.data.data.local.entity.Artist
import com.mes.shiestywave.data.data.local.entity.FeaturedArtist
import com.mes.shiestywave.data.data.local.entity.Song
import com.mes.shiestywave.data.data.local.entity.unknownArtist
import com.mes.shiestywave.data.repository.ArtistRepository
import com.mes.shiestywave.data.repository.FeaturedArtistRepository
import com.mes.shiestywave.data.repository.SongRepository
import com.mes.shiestywave.domain.model.ArtistUiModel
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
                artist = artistRepository.getArtist(song.artistId),
                featuredArtists = featuredArtistRepository.getFeaturedArtists(song.id).first().map {
                    artistRepository.getArtist(it.artistId) ?: unknownArtist
                }
            )
        }
    }

    fun save(songs: List<Song>) = songRepository.save(songs)
    suspend fun nukeDb() = songRepository.nukeDb()
}

class ArtistUseCase(
    private val artistRepository: ArtistRepository,
    private val featuredArtistRepository: FeaturedArtistRepository
) {
    fun artistSongs(artist: String) = artistRepository.fetchArtistSongs(artist = artist)
    fun allArtists() = artistRepository.fetchAllArtists().map {
        artists ->
        artists.map {
            artist ->
            ArtistUiModel.ArtistModel(artist = artist)
        }
    }
    suspend fun get(id: String) = artistRepository.getArtist(artist = id)

    fun save(artists: List<Artist>) = artistRepository.save(artists)
    fun saveFeaturedArtists(featuredArtists: List<FeaturedArtist>) =
        featuredArtistRepository.save(featuredArtists)
}
