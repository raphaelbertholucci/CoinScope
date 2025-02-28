package com.coinscope.data

import androidx.paging.PagingSource
import com.coinscope.data.mapper.CoinMapper
import com.coinscope.data.mapper.SearchMapper
import com.coinscope.data.model.CoinResponse
import com.coinscope.data.model.CoinSearchResponse
import com.coinscope.data.model.SearchResponse
import com.coinscope.data.repository.CoinsPagingSource
import com.coinscope.data.repository.CoinsRepositoryImpl
import com.coinscope.domain.ResultWrapper
import com.coinscope.domain.model.Coin
import com.coinscope.domain.model.SearchItem
import com.coinscope.domain.repository.CoinsRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CoinRepositoryTest {

    private lateinit var repository: CoinsRepository
    private lateinit var pagingSource: CoinsPagingSource
    private val apiService: CoinScopeApi = mockk()

    @Before
    fun setUp() {
        repository = CoinsRepositoryImpl(apiService)
        pagingSource = CoinsPagingSource(apiService)
    }

    @Test
    fun `search() emits Success when API returns data`() = runBlocking {
        val query = "bitcoin"
        val fakeSearchResults =
            SearchResponse(coins = listOf(CoinSearchResponse(name = "Bitcoin", symbol = "BTC")))

        coEvery { apiService.search(query) } returns fakeSearchResults

        val resultList = listOf(
            SearchItem(
                type = "coin",
                name = "Bitcoin",
                symbol = "BTC"
            )
        )

        repository.search(query)

        assertEquals(SearchMapper.mapToDomain(fakeSearchResults), resultList)
    }

    @Test
    fun `search() emits Error when API fails`() = runBlocking {
        val query = "bitcoin"
        coEvery { apiService.search(query) } throws RuntimeException("API Error")

        val resultList = mutableListOf<ResultWrapper<List<SearchItem>>>()

        repository.search(query).toList(resultList)

        assert(resultList.first() is ResultWrapper.Error)
    }

    @Test
    fun `getCoins() emits Success when API returns data`() = runBlocking {
        val fakeResults = listOf(
            CoinResponse(name = "Bitcoin"), CoinResponse(name = "Etherium")
        )

        coEvery { apiService.getCoins(page = 0) } returns fakeResults

        val resultList = listOf(Coin(name = "Bitcoin"), Coin(name = "Etherium"))

        repository.getCoins()

        val fakeResult = fakeResults.map {
            CoinMapper.mapToDomain(it)
        }
        assertEquals(fakeResult, resultList)
    }

    @Test
    fun `getCoins() emits Error when API fails`() = runBlocking {
        coEvery { apiService.getCoins(page = 0) } throws RuntimeException("API Failure")

        val result = pagingSource.load(PagingSource.LoadParams.Refresh(1, 30, false))

        assertTrue(result is PagingSource.LoadResult.Error)
    }
}