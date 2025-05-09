package com.coinscope.domain.di

import com.coinscope.domain.interactor.GetCoinDetailsByID
import com.coinscope.domain.interactor.GetCoins
import com.coinscope.domain.interactor.GetExchanges
import com.coinscope.domain.interactor.SearchQuery
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factory { GetCoins(repository = get()) }
    factory { SearchQuery(repository = get()) }
    factory { GetExchanges(repository = get()) }
    factory { GetCoinDetailsByID(repository = get()) }
}