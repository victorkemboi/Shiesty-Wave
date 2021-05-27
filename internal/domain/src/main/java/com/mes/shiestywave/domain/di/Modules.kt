package com.mes.shiestywave.domain.di

import com.mes.shiestywave.domain.usecase.ArtistUseCase
import com.mes.shiestywave.domain.usecase.SongUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

private val useCaseModule: Module = module {
    single { SongUseCase(get(), get(), get()) }
    single { ArtistUseCase(get(), get()) }
}

val domainModules: List<Module> = listOf(
    useCaseModule
)
