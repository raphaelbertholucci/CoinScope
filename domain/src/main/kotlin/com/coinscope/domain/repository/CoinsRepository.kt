package com.coinscope.domain.repository

import androidx.paging.PagingData
import com.coinscope.domain.ResultWrapper
import com.coinscope.domain.model.Coin
import com.coinscope.domain.model.Exchange
import com.coinscope.domain.model.SearchItem
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {
    fun getCoins(): Flow<ResultWrapper<List<Coin>>>
    fun search(query: String): Flow<ResultWrapper<List<SearchItem>>>
    fun getExchanges(): Flow<PagingData<Exchange>>
}