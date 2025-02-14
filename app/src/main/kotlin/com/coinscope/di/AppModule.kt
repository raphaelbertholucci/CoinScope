package com.coinscope.di

import com.coinscope.ui.coins.CoinsViewModel
import com.coinscope.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CoinsViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}