package com.coinscope.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.coinscope.data.CoinScopeApi
import com.coinscope.data.helpers.safeApiCall
import com.coinscope.data.mapper.SearchMapper
import com.coinscope.domain.ResultWrapper
import com.coinscope.domain.model.Coin
import com.coinscope.domain.model.Exchange
import com.coinscope.domain.model.SearchItem
import com.coinscope.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow

class CoinsRepositoryImpl(private val api: CoinScopeApi) : CoinsRepository {

    override fun getCoins(): Flow<PagingData<Coin>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { CoinsPagingSource(api) }
        ).flow
    }

    override fun search(query: String): Flow<ResultWrapper<List<SearchItem>>> {
        return safeApiCall {
            SearchMapper.mapToDomain(api.search(query = query))
        }
    }

    override fun getExchanges(): Flow<PagingData<Exchange>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { ExchangesPagingSource(api) }
        ).flow
    }
}