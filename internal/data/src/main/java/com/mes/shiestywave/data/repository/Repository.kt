package com.mes.shiestywave.data.repository

import com.mes.shiestywave.data.data.local.dao.ArtistDao
import com.mes.shiestywave.data.data.local.dao.FeaturedArtistDao
import com.mes.shiestywave.data.data.local.dao.SongDao

interface ArtistRepository

class ArtistRepositoryImpl(
    private val artistDao: ArtistDao
) : ArtistRepository

interface FeaturedArtistRepository

class FeaturedArtistRepositoryImpl(
    private val featuredArtistDao: FeaturedArtistDao
) : FeaturedArtistRepository

interface SongRepository

class SongRepositoryImpl(
    private val songDao: SongDao
) : SongRepository
