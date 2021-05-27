package com.mes.shiestywave.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mes.shiestywave.data.data.local.dao.ArtistDao
import com.mes.shiestywave.data.data.local.dao.FeaturedArtistDao
import com.mes.shiestywave.data.data.local.dao.SongDao
import com.mes.shiestywave.data.data.local.entity.Artist
import com.mes.shiestywave.data.data.local.entity.FeaturedArtist
import com.mes.shiestywave.data.data.local.entity.Song
import kotlinx.coroutines.flow.Flow

interface ArtistRepository {
    fun fetchAllArtists(): Flow<PagingData<Artist>>
    fun fetchArtistSongs(artist: String): Flow<PagingData<Song>>
    suspend fun getArtist(artist: String): Artist?
}

class ArtistRepositoryImpl(
    private val artistDao: ArtistDao,
    private val songDao: SongDao
) : ArtistRepository {
    @ExperimentalPagingApi
    override fun fetchAllArtists(): Flow<PagingData<Artist>> =
        Pager(
            config = PagingConfig(
                pageSize = 50,
                enablePlaceholders = false,
                prefetchDistance = 2
            ),
            remoteMediator = null,
            pagingSourceFactory = {
                artistDao.fetchAllArtists()
            }
        ).flow

    @ExperimentalPagingApi
    override fun fetchArtistSongs(artist: String): Flow<PagingData<Song>> =
        Pager(
            config = PagingConfig(
                pageSize = 50,
                enablePlaceholders = false,
                prefetchDistance = 2
            ),
            remoteMediator = null,
            pagingSourceFactory = {
                songDao.fetchArtistSongs(artist = artist)
            }
        ).flow

    override suspend fun getArtist(artist: String): Artist? =
        artistDao.fetchArtistById(artist)
}

interface FeaturedArtistRepository {
    fun getFeaturedArtists(song: String): Flow<List<FeaturedArtist>>
}

class FeaturedArtistRepositoryImpl(
    private val featuredArtistDao: FeaturedArtistDao
) : FeaturedArtistRepository {
    override fun getFeaturedArtists(song: String): Flow<List<FeaturedArtist>> =
        featuredArtistDao.fetchFeaturedArtists(song = song)
}

interface SongRepository {
    fun fetchAllSongs(): Flow<PagingData<Song>>
    suspend fun getSong(id: String): Song?
}

class SongRepositoryImpl(
    private val songDao: SongDao
) : SongRepository {
    @ExperimentalPagingApi
    override fun fetchAllSongs(): Flow<PagingData<Song>> =
        Pager(
            config = PagingConfig(
                pageSize = 50,
                enablePlaceholders = false,
                prefetchDistance = 2
            ),
            remoteMediator = null,
            pagingSourceFactory = {
                songDao.fetchAllSongs()
            }
        ).flow

    override suspend fun getSong(id: String): Song? =
        songDao.fetchSongById(id)
}
