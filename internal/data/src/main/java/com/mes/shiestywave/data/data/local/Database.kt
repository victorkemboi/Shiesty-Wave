package com.mes.shiestywave.data.data.local

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.rejareja.data.data.local.converters.DateTimeConverter
import com.mes.shiestywave.data.data.local.dao.ArtistCoverDao
import com.mes.shiestywave.data.data.local.dao.ArtistDao
import com.mes.shiestywave.data.data.local.dao.FeaturedArtistDao
import com.mes.shiestywave.data.data.local.dao.SongDao
import com.mes.shiestywave.data.data.local.entity.Artist
import com.mes.shiestywave.data.data.local.entity.ArtistCover
import com.mes.shiestywave.data.data.local.entity.FeaturedArtist
import com.mes.shiestywave.data.data.local.entity.Song

@androidx.room.Database(
    entities = [
        Artist::class,
        Song::class,
        FeaturedArtist::class,
        ArtistCover::class,
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(
    DateTimeConverter::class,
)
abstract class Database : RoomDatabase() {
    abstract fun ArtistDao(): ArtistDao
    abstract fun SongDao(): SongDao
    abstract fun ArtistCoverDao(): ArtistCoverDao
    abstract fun FeaturedArtistDao(): FeaturedArtistDao
}
