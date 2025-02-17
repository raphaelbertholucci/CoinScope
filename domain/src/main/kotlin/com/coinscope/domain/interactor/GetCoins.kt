package com.coinscope.domain.interactor

import androidx.paging.PagingData
import com.coinscope.domain.UseCase
import com.coinscope.domain.model.Coin
import com.coinscope.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow

class GetCoins(private val repository: CoinsRepository) : UseCase<Unit, PagingData<Coin>>() {

    override fun executeUseCase(requestValues: Unit): Flow<PagingData<Coin>> {
        return repository.getCoins()
    }
}