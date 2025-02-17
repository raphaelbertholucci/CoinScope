package com.coinscope.data.mapper

import com.coinscope.data.BaseMapper
import com.coinscope.data.model.ExchangeResponse
import com.coinscope.domain.model.Exchange

object ExchangeMapper : BaseMapper<ExchangeResponse, Exchange> {
    override fun mapFromDomain(domain: Exchange): ExchangeResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: ExchangeResponse): Exchange {
        return Exchange(
            country = response.country,
            description = response.description,
            hasTradingIncentive = response.hasTradingIncentive,
            id = response.id,
            image = response.image,
            name = response.name,
            tradeVolume24hBtc = response.tradeVolume24hBtc,
            tradeVolume24hBtcNormalized = response.tradeVolume24hBtcNormalized,
            trustScore = response.trustScore,
            trustScoreRank = response.trustScoreRank,
            url = response.url,
            yearEstablished = response.yearEstablished
        )
    }
}