package com.coinscope.domain.interactor

import com.coinscope.domain.ResultWrapper
import com.coinscope.domain.WrapperUseCase
import com.coinscope.domain.model.SearchItem
import com.coinscope.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow

class SearchQuery(private val repository: CoinsRepository) :
    WrapperUseCase<String, List<SearchItem>>() {

    override fun executeUseCase(requestValues: String): Flow<ResultWrapper<List<SearchItem>>> {
        return repository.search(requestValues)
    }
}