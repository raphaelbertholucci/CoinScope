package com.coinscope.data.mapper

import com.coinscope.data.BaseMapper
import com.coinscope.data.model.SearchResponse
import com.coinscope.domain.model.SearchItem

object SearchMapper : BaseMapper<SearchResponse, List<SearchItem>> {
    override fun mapFromDomain(domain: List<SearchItem>): SearchResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: SearchResponse): List<SearchItem> {
        val coinItems = response.coins?.map {
            SearchItem(
                type = "coin",
                apiSymbol = it.apiSymbol,
                id = it.id,
                large = it.large,
                marketCapRank = it.marketCapRank,
                name = it.name,
                symbol = it.symbol,
                thumb = it.thumb
            )
        } ?: emptyList()

        val exchangeItems = response.exchanges?.map {
            SearchItem(
                type = "exchange",
                id = it.id,
                large = it.large,
                marketType = it.marketType,
                name = it.name,
                thumb = it.thumb
            )
        } ?: emptyList()

        val categoryItems = response.categories?.map {
            SearchItem(
                type = "category",
                id = it.id,
                name = it.name
            )
        } ?: emptyList()

        val nftItems = response.nfts?.map {
            SearchItem(
                type = "nft",
                id = it.id,
                name = it.name,
                symbol = it.symbol,
                thumb = it.thumb
            )
        } ?: emptyList()

        return coinItems + exchangeItems + categoryItems + nftItems
    }
}
