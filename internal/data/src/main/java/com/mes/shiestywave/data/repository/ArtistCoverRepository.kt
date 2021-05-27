package com.mes.shiestywave.data.repository

import com.mes.shiestywave.data.data.local.dao.ArtistCoverDao
import com.mes.shiestywave.data.data.local.entity.ArtistCover
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ArtistCoverRepository : BaseRepository<ArtistCover> {
    fun fetchArtistCovers(artist: String): Flow<List<ArtistCover>>
}

class ArtistCoverRepositoryImpl(
    private val artistCoverDao: ArtistCoverDao
) : ArtistCoverRepository {

    override fun fetchArtistCovers(artist: String): Flow<List<ArtistCover>> =
        artistCoverDao.fetchArtistCovers(artist = artist)

    override fun save(item: ArtistCover): Flow<Long> =
        flow { emit(artistCoverDao.insert(item)) }

    override fun save(items: List<ArtistCover>): Flow<List<Long>> =
        flow { emit(artistCoverDao.insert(items)) }

    override suspend fun update(item: ArtistCover) {
        artistCoverDao.update(item)
    }

    override suspend fun update(items: List<ArtistCover>) {
        artistCoverDao.update(items)
    }

    override suspend fun delete(item: ArtistCover) {
        artistCoverDao.delete(item)
    }
}
