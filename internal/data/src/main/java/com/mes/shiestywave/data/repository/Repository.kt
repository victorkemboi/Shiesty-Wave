package com.mes.shiestywave.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mes.shiestywave.data.data.local.Database
import com.mes.shiestywave.data.data.local.dao.ArtistDao
import com.mes.shiestywave.data.data.local.dao.FeaturedArtistDao
import com.mes.shiestywave.data.data.local.dao.SongDao
import com.mes.shiestywave.data.data.local.entity.Artist
import com.mes.shiestywave.data.data.local.entity.FeaturedArtist
import com.mes.shiestywave.data.data.local.entity.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ArtistRepository : BaseRepository<Artist> {
    fun fetchAllArtists(): Flow<PagingData<Artist>>
    fun fetchPagedArtistSongs(artist: String): Flow<PagingData<Song>>
    fun fetchArtistSongs(artist: String): Flow<List<Song>>
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
    override fun fetchPagedArtistSongs(artist: String): Flow<PagingData<Song>> =
        Pager(
            config = PagingConfig(
                pageSize = 50,
                enablePlaceholders = false,
                prefetchDistance = 2
            ),
            remoteMediator = null,
            pagingSourceFactory = {
                songDao.fetchPagedArtistSongs(artist = artist)
            }
        ).flow

    override fun fetchArtistSongs(artist: String): Flow<List<Song>> =
        songDao.fetchPagedArtistSongs(artist = artist)

    override suspend fun getArtist(artist: String): Artist? =
        artistDao.fetchArtistById(artist)

    override fun save(item: Artist): Flow<Long> =
        flow { emit(artistDao.insert(item)) }

    override fun save(items: List<Artist>): Flow<List<Long>> =
        flow { emit(artistDao.insert(items)) }

    override suspend fun update(item: Artist) {
        artistDao.update(item)
    }

    override suspend fun update(items: List<Artist>) {
        artistDao.update(items)
    }

    override suspend fun delete(item: Artist) {
        artistDao.delete(item)
    }
}

interface FeaturedArtistRepository : BaseRepository<FeaturedArtist> {
    fun getFeaturedArtists(song: String): Flow<List<FeaturedArtist>>
}

class FeaturedArtistRepositoryImpl(
    private val featuredArtistDao: FeaturedArtistDao
) : FeaturedArtistRepository {
    override fun getFeaturedArtists(song: String): Flow<List<FeaturedArtist>> =
        featuredArtistDao.fetchFeaturedArtists(song = song)

    override fun save(item: FeaturedArtist): Flow<Long> =
        flow { emit(featuredArtistDao.insert(item)) }

    override fun save(items: List<FeaturedArtist>): Flow<List<Long>> =
        flow { emit(featuredArtistDao.insert(items)) }

    override suspend fun update(item: FeaturedArtist) {
        featuredArtistDao.update(item)
    }

    override suspend fun update(items: List<FeaturedArtist>) {
        featuredArtistDao.update(items)
    }

    override suspend fun delete(item: FeaturedArtist) {
        featuredArtistDao.delete(item)
    }
}

interface SongRepository : BaseRepository<Song> {
    fun fetchAllSongs(): Flow<PagingData<Song>>
    suspend fun getSong(id: String): Song?
    suspend fun nukeDb()
}

class SongRepositoryImpl(
    private val songDao: SongDao,
    private val db: Database
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

    override suspend fun nukeDb() {
        db.clearAllTables()
    }

    override fun save(item: Song): Flow<Long> =
        flow { emit(songDao.insert(item)) }

    override fun save(items: List<Song>): Flow<List<Long>> =
        flow { emit(songDao.insert(items)) }

    override suspend fun update(item: Song) {
        songDao.update(item)
    }

    override suspend fun update(items: List<Song>) {
        songDao.update(items)
    }

    override suspend fun delete(item: Song) {
        songDao.delete(item)
    }
}
