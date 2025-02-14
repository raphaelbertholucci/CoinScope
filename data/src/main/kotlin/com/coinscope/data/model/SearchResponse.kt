package com.coinscope.data.model


import com.google.gson.annotations.SerializedName

class SearchResponse(
    @SerializedName("coins") val coins: List<CoinSearchResponse>? = null,
    @SerializedName("exchanges") val exchanges: List<ExchangeResponse>? = null,
    @SerializedName("categories") val categories: List<CategoryResponse>? = null,
    @SerializedName("nfts") val nfts: List<NftResponse>? = null
)

class CoinSearchResponse(
    @SerializedName("api_symbol") val apiSymbol: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("large") val large: String? = null,
    @SerializedName("market_cap_rank") val marketCapRank: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("symbol") val symbol: String? = null,
    @SerializedName("thumb") val thumb: String? = null
)

class ExchangeResponse(
    @SerializedName("id") val id: String? = null,
    @SerializedName("large") val large: String? = null,
    @SerializedName("market_type") val marketType: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("thumb") val thumb: String? = null
)

class CategoryResponse(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null
)

class NftResponse(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("symbol") val symbol: String? = null,
    @SerializedName("thumb") val thumb: String? = null
)