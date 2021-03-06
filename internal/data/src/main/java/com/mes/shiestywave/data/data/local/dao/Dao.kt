package com.mes.shiestywave.data.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import app.rejareja.data.data.local.base.BaseDao
import com.mes.shiestywave.data.data.local.entity.Artist
import com.mes.shiestywave.data.data.local.entity.ArtistCover
import com.mes.shiestywave.data.data.local.entity.FeaturedArtist
import com.mes.shiestywave.data.data.local.entity.Song
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao : BaseDao<Artist> {

    @Query("SELECT * FROM Artist WHERE id =:id LIMIT 1")
    suspend fun fetchArtistById(id: String): Artist?

    @Query("SELECT * FROM Artist")
    fun fetchAllArtists(): PagingSource<Int, Artist>
}

@Dao
interface SongDao : BaseDao<Song> {

    @Query("SELECT * FROM Song WHERE id =:id LIMIT 1")
    suspend fun fetchSongById(id: String): Song?

    @Query("SELECT * FROM Song")
    fun fetchAllSongs(): PagingSource<Int, Song>

    @Query("SELECT * FROM Song WHERE id=:artist")
    fun fetchPagedArtistSongs(artist: String): PagingSource<Int, Song>

    @Query("SELECT * FROM Song WHERE id=:artist")
    fun fetchArtistSongs(artist: String): Flow<List<Song>>
}

@Dao
interface FeaturedArtistDao : BaseDao<FeaturedArtist> {

    @Query("SELECT * FROM FeaturedArtist WHERE id =:id LIMIT 1")
    suspend fun fetchFeaturedArtistById(id: String): FeaturedArtist?

    @Query("SELECT * FROM FeaturedArtist WHERE songId=:song")
    fun fetchFeaturedArtists(song: String): Flow<List<FeaturedArtist>>
}

@Dao
interface ArtistCoverDao : BaseDao<ArtistCover> {

    @Query("SELECT * FROM ArtistCover WHERE id =:id LIMIT 1")
    suspend fun fetchFeaturedArtistById(id: String): ArtistCover?

    @Query("SELECT * FROM ArtistCover WHERE artistId=:artist")
    fun fetchArtistCovers(artist: String): Flow<List<ArtistCover>>
}
