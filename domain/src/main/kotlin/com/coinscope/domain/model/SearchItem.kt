package com.coinscope.domain.model

data class SearchItem(
    val type: String,
    val apiSymbol: String? = null,
    val id: String? = null,
    val large: String? = null,
    val marketCapRank: Int? = null,
    val marketType: String? = null,
    val name: String? = null,
    val symbol: String? = null,
    val thumb: String? = null
)