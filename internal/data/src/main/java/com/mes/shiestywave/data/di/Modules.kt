package com.mes.shiestywave.data.di

import androidx.room.Room
import com.mes.shiestywave.data.data.local.Database
import com.mes.shiestywave.data.repository.* // ktlint-disable no-wildcard-imports
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val databaseModule: Module = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java,
            "shiesty-db"
        ).fallbackToDestructiveMigration().build()
    }
}

val apiModule: Module = module {
}

private val daoModule: Module = module {
    single { get<Database>().ArtistDao() }
    single { get<Database>().FeaturedArtistDao() }
    single { get<Database>().SongDao() }
}

private val repositoryModule: Module = module {
    single<ArtistRepository> { ArtistRepositoryImpl(get(), get()) }
    single<FeaturedArtistRepository> { FeaturedArtistRepositoryImpl(get()) }
    single<SongRepository> { SongRepositoryImpl(get()) }
}

val dataModules: List<Module> = listOf(
    databaseModule,
    apiModule,
    daoModule,
    repositoryModule
)
