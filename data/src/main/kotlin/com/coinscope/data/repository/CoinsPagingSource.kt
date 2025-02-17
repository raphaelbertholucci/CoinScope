package com.coinscope.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.coinscope.data.CoinScopeApi
import com.coinscope.data.mapper.CoinMapper
import com.coinscope.domain.model.Coin

class CoinsPagingSource(
    private val apiService: CoinScopeApi
) : PagingSource<Int, Coin>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        return try {
            val page = params.key ?: 1 // Default to first page
            val response =
                apiService.getCoins(page = page).map { CoinMapper.mapToDomain(it) }

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}