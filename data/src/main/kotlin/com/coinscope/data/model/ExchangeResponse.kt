package com.coinscope.data.model


import com.google.gson.annotations.SerializedName

class ExchangeResponse(
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("has_trading_incentive")
    val hasTradingIncentive: Boolean? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("trade_volume_24h_btc")
    val tradeVolume24hBtc: Double? = null,
    @SerializedName("trade_volume_24h_btc_normalized")
    val tradeVolume24hBtcNormalized: Double? = null,
    @SerializedName("trust_score")
    val trustScore: Int? = null,
    @SerializedName("trust_score_rank")
    val trustScoreRank: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("year_established")
    val yearEstablished: Int? = null
)