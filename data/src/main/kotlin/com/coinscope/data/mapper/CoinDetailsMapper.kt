package com.coinscope.data.mapper

import com.coinscope.data.BaseMapper
import com.coinscope.data.model.CoinDetailsResponse
import com.coinscope.data.model.DescriptionResponse
import com.coinscope.data.model.ImageResponse
import com.coinscope.data.model.LinkResponse
import com.coinscope.data.model.MarketDataResponse
import com.coinscope.data.model.ReposUrlResponse
import com.coinscope.domain.model.CoinDetails
import com.coinscope.domain.model.Image
import com.coinscope.domain.model.Link
import com.coinscope.domain.model.MarketData
import com.coinscope.domain.model.ReposUrl

object CoinDetailsMapper : BaseMapper<CoinDetailsResponse, CoinDetails> {
    override fun mapFromDomain(domain: CoinDetails): CoinDetailsResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: CoinDetailsResponse): CoinDetails {
        return CoinDetails(
            id = response.id.orEmpty(),
            symbol = response.symbol,
            name = response.name,
            hashingAlgorithm = response.hashingAlgorithm,
            categories = response.categories,
            description = response.description?.let { DescriptionMapper.mapToDomain(it) },
            links = response.links?.let { LinksMapper.mapToDomain(it) },
            image = response.image?.let { ImageMapper.mapToDomain(it) },
            genesisDate = response.genesisDate,
            rank = response.rank,
            marketData = response.marketData?.let { MarketDataMapper.mapToDomain(it) }
        )
    }
}

object DescriptionMapper : BaseMapper<DescriptionResponse, String> {
    override fun mapFromDomain(domain: String): DescriptionResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: DescriptionResponse): String {
        return response.english.orEmpty()
    }
}

object LinksMapper : BaseMapper<LinkResponse, Link> {
    override fun mapFromDomain(domain: Link): LinkResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: LinkResponse): Link {
        return Link(
            homepage = response.homepage,
            officialForumUrl = response.officialForumUrl,
            reposUrl = response.reposUrl?.let { ReposUrlMapper.mapToDomain(it) },
        )
    }
}

object ReposUrlMapper : BaseMapper<ReposUrlResponse, ReposUrl> {
    override fun mapFromDomain(domain: ReposUrl): ReposUrlResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: ReposUrlResponse): ReposUrl {
        return ReposUrl(
            bitbucket = response.bitbucket, github = response.github
        )
    }
}

object ImageMapper : BaseMapper<ImageResponse, Image> {
    override fun mapFromDomain(domain: Image): ImageResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: ImageResponse): Image {
        return Image(
            large = response.large, small = response.small, thumb = response.thumb
        )
    }
}

object MarketDataMapper : BaseMapper<MarketDataResponse, MarketData> {
    override fun mapFromDomain(domain: MarketData): MarketDataResponse {
        throw UnsupportedOperationException("Operation not supported!")
    }

    override fun mapToDomain(response: MarketDataResponse): MarketData {
        return MarketData(
            price = response.price?.usd,
            high24h = response.high24h?.usd,
            low24h = response.low24h?.usd,
            priceChange24h = response.priceChange24h
        )
    }
}