package com.coinscope.domain.model

data class Exchange(
    val country: String? = null,
    val description: String? = null,
    val hasTradingIncentive: Boolean? = null,
    val id: String? = null,
    val image: String? = null,
    val name: String? = null,
    val tradeVolume24hBtc: Double? = null,
    val tradeVolume24hBtcNormalized: Double? = null,
    val trustScore: Int? = null,
    val trustScoreRank: Int? = null,
    val url: String? = null,
    val yearEstablished: Int? = null
)