package com.coinscope.domain.model

data class CoinDetails(
    val id: String? = null,
    val symbol: String? = null,
    val name: String? = null,
    val hashingAlgorithm: String? = null,
    val categories: List<String>? = null,
    val description: String? = null,
    val links: Link? = null,
    val image: Image? = null,
    val genesisDate: String? = null,
    val rank: Int? = null,
    val marketData: MarketData? = null
)

data class Link(
    val homepage: List<String>? = null,
    val officialForumUrl: List<String>? = null,
    val reposUrl: ReposUrl? = null
)

data class ReposUrl(
    val bitbucket: List<String>? = null,
    val github: List<String>? = null
)

data class Image(
    val large: String? = null,
    val small: String? = null,
    val thumb: String? = null
)

data class MarketData(
    val price: Double?,
    val high24h: Double?,
    val low24h: Double?,
    val priceChange24h: Double?
)