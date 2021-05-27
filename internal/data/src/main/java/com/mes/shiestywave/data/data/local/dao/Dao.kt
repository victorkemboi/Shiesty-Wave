package com.mes.shiestywave.data.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import app.rejareja.data.data.local.base.BaseDao
import com.mes.shiestywave.data.data.local.entity.Artist
import com.mes.shiestywave.data.data.local.entity.FeaturedArtist
import com.mes.shiestywave.data.data.local.entity.Song
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao : BaseDao<Artist> {

    @Query("SELECT * FROM Artist WHERE id =:id LIMIT 1")
    fun fetchArtistById(id: String): Flow<Artist>
}

@Dao
interface SongDao : BaseDao<Song> {

    @Query("SELECT * FROM Song WHERE id =:id LIMIT 1")
    fun fetchSongById(id: String): Flow<Song>
}

@Dao
interface FeaturedArtistDao : BaseDao<FeaturedArtist> {

    @Query("SELECT * FROM FeaturedArtist WHERE id =:id LIMIT 1")
    fun fetchFeaturedArtistById(id: String): Flow<FeaturedArtist>
}
