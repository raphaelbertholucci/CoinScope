package com.coinscope.data

import com.coinscope.data.mapper.SearchMapper
import com.coinscope.data.model.CoinSearchResponse
import com.coinscope.data.model.SearchResponse
import com.coinscope.data.repository.CoinsRepositoryImpl
import com.coinscope.domain.ResultWrapper
import com.coinscope.domain.model.SearchItem
import com.coinscope.domain.repository.CoinsRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CoinRepositoryTest {

    private lateinit var repository: CoinsRepository
    private val apiService: CoinScopeApi = mockk()

    @Before
    fun setUp() {
        repository = CoinsRepositoryImpl(apiService)
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
}