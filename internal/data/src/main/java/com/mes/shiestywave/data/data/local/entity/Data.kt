package com.mes.shiestywave.data.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mes.shiestywave.data.generateLocalID

@Entity(indices = [Index(value = ["id"], unique = true)])
data class Song(
    val name: String,
    val artist: Artist,
    @PrimaryKey
    val id: String = generateLocalID()
)

@Entity(indices = [Index(value = ["id"], unique = true)])
data class Artist(
    var name: String,
    @PrimaryKey
    val id: String = generateLocalID()
)

@Entity(indices = [Index(value = ["id"], unique = true)])
data class FeaturedArtist(
    var song: String,
    var artist: String,
    @PrimaryKey
    val id: String = generateLocalID()
)
