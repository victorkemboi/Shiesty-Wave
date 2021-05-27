package com.mes.shiestywave.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.mes.shiestywave.domain.usecase.ArtistUseCase
import com.mes.shiestywave.domain.usecase.SongUseCase

class SongViewModel(
    private val songUseCase: SongUseCase
) : ViewModel() {
    fun getSongs() = songUseCase.allSongs()
    suspend fun getSong(id: String) = songUseCase.get(id = id)
}

class ArtistViewModel(
    private val artistUseCase: ArtistUseCase
) : ViewModel() {
    fun getArtists() = artistUseCase.allArtists()
    suspend fun getArtist(id: String) = artistUseCase.get(id)
}
