package com.coinscope.domain.di

import com.coinscope.domain.interactor.GetCoins
import org.koin.dsl.module

val domainModule = module {
    factory { GetCoins(repository = get()) }
}