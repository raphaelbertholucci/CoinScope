package com.coinscope

import com.coinscope.data.di.dataModule
import com.coinscope.data.di.repositoryModule
import com.coinscope.di.appModule
import com.coinscope.domain.di.domainModule

val modules = listOf(
    domainModule,
    dataModule,
    repositoryModule,
    appModule
)
