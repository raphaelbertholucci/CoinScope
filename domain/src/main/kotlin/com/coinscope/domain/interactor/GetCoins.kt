package com.coinscope.domain.interactor

import com.coinscope.domain.ResultWrapper
import com.coinscope.domain.WrapperUseCase
import com.coinscope.domain.model.Coin
import com.coinscope.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow

class GetCoins(private val repository: CoinsRepository) : WrapperUseCase<Unit, List<Coin>>() {

    override fun executeUseCase(requestValues: Unit): Flow<ResultWrapper<List<Coin>>> {
        return repository.getCoins()
    }
}