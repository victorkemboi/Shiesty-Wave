package app.rejareja.domain.di

import app.rejareja.domain.usecase.*
import org.koin.core.module.Module
import org.koin.dsl.module

private val useCaseModule: Module = module {
}

val domainModules: List<Module> = listOf(
    useCaseModule
)