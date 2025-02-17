package com.coinscope.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.coinscope.data.CoinScopeApi
import com.coinscope.data.mapper.ExchangeMapper
import com.coinscope.domain.model.Exchange


class CoinsPagingSource(
    private val apiService: CoinScopeApi
) : PagingSource<Int, Exchange>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Exchange> {
        return try {
            val page = params.key ?: 1 // Default to first page
            val response =
                apiService.getExchanges(page = page).map { ExchangeMapper.mapToDomain(it) }

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Exchange>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}