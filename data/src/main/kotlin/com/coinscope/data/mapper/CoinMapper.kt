package com.coinscope.data.mapper

import com.coinscope.data.BaseMapper
import com.coinscope.data.model.CoinResponse
import com.coinscope.domain.model.Coin

object CoinMapper : BaseMapper<CoinResponse, Coin> {
    override fun mapFromDomain(domain: Coin): CoinResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: CoinResponse): Coin {
        return Coin(
            ath = response.ath,
            athChangePercentage = response.athChangePercentage,
            athDate = response.athDate,
            atl = response.atl,
            atlChangePercentage = response.atlChangePercentage,
            atlDate = response.atlDate,
            circulatingSupply = response.circulatingSupply,
            currentPrice = response.currentPrice,
            fullyDilutedValuation = response.fullyDilutedValuation,
            high24h = response.high24h,
            id = response.id,
            image = response.image,
            lastUpdated = response.lastUpdated,
            low24h = response.low24h,
            marketCap = response.marketCap,
            marketCapChange24h = response.marketCapChange24h,
            marketCapChangePercentage24h = response.marketCapChangePercentage24h,
            marketCapRank = response.marketCapRank,
            maxSupply = response.maxSupply,
            name = response.name,
            priceChange24h = response.priceChange24h,
            priceChangePercentage24h = response.priceChangePercentage24h,
            roi = response.roi,
            symbol = response.symbol,
            totalSupply = response.totalSupply,
            totalVolume = response.totalVolume
        )
    }
}