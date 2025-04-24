package com.coinscope.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.coinscope.data.CoinScopeApi
import com.coinscope.data.helpers.safeApiCall
import com.coinscope.data.mapper.CoinDetailsMapper
import com.coinscope.data.mapper.SearchMapper
import com.coinscope.domain.ResultWrapper
import com.coinscope.domain.model.Coin
import com.coinscope.domain.model.CoinDetails
import com.coinscope.domain.model.Exchange
import com.coinscope.domain.model.SearchItem
import com.coinscope.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CoinsRepositoryImpl(private val api: CoinScopeApi) : CoinsRepository {

    private val mutex = Mutex()
    private val coinDetailsCache = mutableMapOf<String, CachedCoinDetails>()

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

    override fun getCoinByID(id: String): Flow<ResultWrapper<CoinDetails>> {
        return safeApiCall {
            mutex.withLock {
                val cached = coinDetailsCache[id]
                val now = System.currentTimeMillis()
                if (cached != null && now - cached.timestamp < CACHE_EXPIRATION_MS) {
                    return@safeApiCall cached.data
                }
                CoinDetailsMapper.mapToDomain(api.getCoinByID(id = id)).also { result ->
                    coinDetailsCache[id] = CachedCoinDetails(result, now)
                }
            }
        }
    }
}

private const val CACHE_EXPIRATION_MS = 5 * 60 * 1000 // 5 minutes

private data class CachedCoinDetails(val data: CoinDetails, val timestamp: Long)