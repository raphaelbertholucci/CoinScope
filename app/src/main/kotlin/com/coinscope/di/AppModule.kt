package com.coinscope.di

import com.coinscope.ui.coins.CoinsViewModel
import com.coinscope.ui.details.CoinDetailsViewModel
import com.coinscope.ui.exchanges.ExchangesViewModel
import com.coinscope.ui.search.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::CoinsViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::ExchangesViewModel)
    viewModelOf(::CoinDetailsViewModel)
}