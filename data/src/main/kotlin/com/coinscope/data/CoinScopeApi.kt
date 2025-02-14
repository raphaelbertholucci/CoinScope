package com.coinscope.data

import com.coinscope.data.model.CoinResponse
import com.coinscope.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinScopeApi {
    @GET("coins/markets")
    suspend fun getCoins(@Query("vs_currency") currency: String = "usd"): List<CoinResponse>

    @GET("search")
    suspend fun search(@Query("query") query: String): SearchResponse
}