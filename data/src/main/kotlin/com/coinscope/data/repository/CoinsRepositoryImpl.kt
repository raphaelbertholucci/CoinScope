package com.coinscope.data.repository

import com.coinscope.data.CoinScopeApi
import com.coinscope.data.helpers.safeApiCall
import com.coinscope.data.mapper.CoinMapper
import com.coinscope.domain.ResultWrapper
import com.coinscope.domain.model.Coin
import com.coinscope.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow

class CoinsRepositoryImpl(private val api: CoinScopeApi) : CoinsRepository {
    override fun getCoins(): Flow<ResultWrapper<List<Coin>>> {
        return safeApiCall {
            api.getCoins().map { CoinMapper.mapToDomain(it) }
        }
    }
}