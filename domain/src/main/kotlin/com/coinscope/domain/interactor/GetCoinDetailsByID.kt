package com.coinscope.domain.interactor

import com.coinscope.domain.ResultWrapper
import com.coinscope.domain.WrapperUseCase
import com.coinscope.domain.model.CoinDetails
import com.coinscope.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow

class GetCoinDetailsByID(private val repository: CoinsRepository) :
    WrapperUseCase<String, CoinDetails>() {

    override fun executeUseCase(requestValues: String): Flow<ResultWrapper<CoinDetails>> {
        return repository.getCoinByID(requestValues)
    }
}