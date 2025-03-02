package com.coinscope.data.model


import com.google.gson.annotations.SerializedName

class CoinDetailsResponse(
    @SerializedName("id") val id: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("name") val name: String,
    @SerializedName("hashing_algorithm") val hashingAlgorithm: String,
    @SerializedName("categories") val categories: List<String>,
    @SerializedName("description") val description: DescriptionResponse,
    @SerializedName("links") val links: LinkResponse,
    @SerializedName("image") val image: ImageResponse,
    @SerializedName("genesis_date") val genesisDate: String,
    @SerializedName("market_cap_rank") val rank: Int,
    @SerializedName("MarketDataResponse") val marketData: MarketDataResponse
)

class DescriptionResponse(
    @SerializedName("en") val english: String
)

class LinkResponse(
    @SerializedName("homepage") val homepage: List<String?>? = null,
    @SerializedName("official_forum_url") val officialForumUrl: List<String>? = null,
    @SerializedName("repos_url") val reposUrl: ReposUrlResponse? = null,
    @SerializedName("subreddit_url") val subredditUrl: String? = null
)

class ReposUrlResponse(
    @SerializedName("bitbucket") val bitbucket: List<String>? = null,
    @SerializedName("github") val github: List<String>? = null
)

class ImageResponse(
    @SerializedName("large") val large: String? = null,
    @SerializedName("small") val small: String? = null,
    @SerializedName("thumb") val thumb: String? = null
)

class MarketDataResponse(
    @SerializedName("current_price") val price: CurrentPriceResponse,
    @SerializedName("high_24h") val high24h: CurrentPriceResponse,
    @SerializedName("low_24h") val low24h: CurrentPriceResponse,
    @SerializedName("price_change_24h") val priceChange24h: Double
)

class CurrentPriceResponse(
    @SerializedName("usd") val usd: Double
)