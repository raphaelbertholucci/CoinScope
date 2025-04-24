package com.coinscope.data.di

import com.coinscope.data.repository.CoinsRepositoryImpl
import com.coinscope.domain.repository.CoinsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CoinsRepository> { CoinsRepositoryImpl(get()) }
}