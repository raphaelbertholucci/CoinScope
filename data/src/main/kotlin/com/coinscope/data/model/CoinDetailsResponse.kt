package com.coinscope.data.model


import com.google.gson.annotations.SerializedName

class CoinDetailsResponse(
    @SerializedName("id") val id: String? = null,
    @SerializedName("symbol") val symbol: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("hashing_algorithm") val hashingAlgorithm: String? = null,
    @SerializedName("categories") val categories: List<String>? = null,
    @SerializedName("description") val description: DescriptionResponse? = null,
    @SerializedName("links") val links: LinkResponse? = null,
    @SerializedName("image") val image: ImageResponse? = null,
    @SerializedName("genesis_date") val genesisDate: String? = null,
    @SerializedName("market_cap_rank") val rank: Int? = null,
    @SerializedName("market_data") val marketData: MarketDataResponse? = null
)

class DescriptionResponse(
    @SerializedName("en") val english: String? = null
)

class LinkResponse(
    @SerializedName("homepage") val homepage: List<String>? = null,
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
    @SerializedName("current_price") val price: CurrentPriceResponse? = null,
    @SerializedName("high_24h") val high24h: CurrentPriceResponse? = null,
    @SerializedName("low_24h") val low24h: CurrentPriceResponse? = null,
    @SerializedName("price_change_24h") val priceChange24h: Double? = null
)

class CurrentPriceResponse(
    @SerializedName("usd") val usd: Double? = null
)