package com.mes.shiestywave.data.repository

import kotlinx.coroutines.flow.Flow

interface BaseRepository<T> {
    fun save(item: T): Flow<Long>
    fun save(items: List<T>): Flow<List<Long>>
    suspend fun update(item: T)
    suspend fun update(items: List<T>)
    suspend fun delete(item: T)
}
