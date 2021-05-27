package com.mes.shiestywave.domain.usecase

import androidx.paging.map
import com.mes.shiestywave.data.data.local.entity.Artist
import com.mes.shiestywave.data.data.local.entity.FeaturedArtist
import com.mes.shiestywave.data.data.local.entity.Song
import com.mes.shiestywave.data.data.local.entity.unknownArtist
import com.mes.shiestywave.data.repository.ArtistRepository
import com.mes.shiestywave.data.repository.FeaturedArtistRepository
import com.mes.shiestywave.data.repository.SongRepository
import com.mes.shiestywave.domain.model.ArtistSongsUiModel
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
                featuredArtists = featuredArtistRepository.getFeaturedArtists(song.id).map {
                    featuredArtists ->
                    featuredArtists.map {
                        val artist = artistRepository.getArtist(it.artistId) ?: unknownArtist
                        artist
                    }
                }.first()
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
    fun pagedArtistSongs(artist: String) =
        artistRepository.fetchPagedArtistSongs(artist = artist).map {
            pagedArtists ->
            pagedArtists.map {
            }
        }

    fun artistSongs(artist: String) = artistRepository.fetchArtistSongs(artist = artist).map {
        songs ->
        songs.map {
            song ->
            SongUiModel.SongModel(
                song = song,
                artist = artistRepository.getArtist(song.artistId),
                featuredArtists = featuredArtistRepository.getFeaturedArtists(
                    song = song.artistId
                ).first().map {
                    getArtist(it.artistId) ?: unknownArtist
                }
            )
        }
    }

    suspend fun getArtist(artist: String) =
        artistRepository.getArtist(artist = artist)

    fun allArtists() = artistRepository.fetchAllArtists().map {
        artists ->
        artists.map {
            artist ->
            ArtistSongsUiModel.ArtistSongsModel(
                artist = ArtistUiModel.ArtistModel(artist = artist),
                songs = artistSongs(artist = artist.id).first()
            )
        }
    }
    suspend fun get(id: String) = artistRepository.getArtist(artist = id)

    fun save(artists: List<Artist>) = artistRepository.save(artists)
    fun saveFeaturedArtists(featuredArtists: List<FeaturedArtist>) =
        featuredArtistRepository.save(featuredArtists)
}
