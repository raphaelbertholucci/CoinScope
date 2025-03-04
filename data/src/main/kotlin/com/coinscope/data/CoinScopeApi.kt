package com.coinscope.data

import com.coinscope.data.model.CoinDetailsResponse
import com.coinscope.data.model.CoinResponse
import com.coinscope.data.model.ExchangeResponse
import com.coinscope.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinScopeApi {
    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String = "usd",
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int
    ): List<CoinResponse>

    @GET("search")
    suspend fun search(@Query("query") query: String): SearchResponse

    @GET("exchanges")
    suspend fun getExchanges(
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int
    ): List<ExchangeResponse>

    @GET("coins/{id}")
    suspend fun getCoinByID(@Path("id") id: String): CoinDetailsResponse
}