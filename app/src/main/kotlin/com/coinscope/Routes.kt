package com.coinscope

import kotlinx.serialization.Serializable

@Serializable
object CoinsRoute

@Serializable
object SearchRoute

@Serializable
object ExchangesRoute

@Serializable
data class CoinDetailsRoute(val id: String)
