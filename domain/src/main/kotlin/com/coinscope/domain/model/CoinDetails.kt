package com.coinscope.domain.model

data class CoinDetails(
    val id: String,
    val symbol: String,
    val name: String,
    val hashingAlgorithm: String,
    val categories: List<String>,
    val description: String,
    val links: Link,
    val image: Image,
    val genesisDate: String,
    val rank: Int,
    val marketData: MarketData
)

data class Link(
    val homepage: List<String?>? = null,
    val officialForumUrl: List<String>? = null,
    val reposUrl: ReposUrl? = null,
    val subredditUrl: String? = null
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
    val price: Double,
    val high24h: Double,
    val low24h: Double,
    val priceChange24h: Double
)