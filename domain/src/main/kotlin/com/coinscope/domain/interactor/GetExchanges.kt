package com.coinscope.domain.interactor

import androidx.paging.PagingData
import com.coinscope.domain.UseCase
import com.coinscope.domain.model.Exchange
import com.coinscope.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow

class GetExchanges(private val repository: CoinsRepository) :
    UseCase<Unit, PagingData<Exchange>>() {

    override fun executeUseCase(requestValues: Unit): Flow<PagingData<Exchange>> {
        return repository.getExchanges()
    }
}