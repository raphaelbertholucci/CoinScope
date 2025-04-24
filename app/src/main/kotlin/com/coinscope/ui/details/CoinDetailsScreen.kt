package com.coinscope.ui.details

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.coinscope.core.UIState
import com.coinscope.core.extensions.formatCurrency
import com.coinscope.core.extensions.formatDate
import com.coinscope.core.extensions.openBrowser
import com.coinscope.design.resources.Dimens
import com.coinscope.design.resources.Dimens.detailsItemHeight
import com.coinscope.design.resources.Shapes
import com.coinscope.design.widget.ErrorContent
import com.coinscope.design.widget.LoadingWidget
import com.coinscope.design.widget.SpacerWidget
import com.coinscope.domain.model.CoinDetails
import com.coinscope.domain.model.Image
import com.coinscope.domain.model.Link
import com.coinscope.domain.model.MarketData
import com.coinscope.domain.model.ReposUrl
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CoinDetailsScreen(
    id: String,
    viewModel: CoinDetailsViewModel = koinViewModel { parametersOf(id) },
    onBack: () -> Unit
) {

    val state by viewModel.details.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            IconButton(
                onClick = { onBack() },
                modifier = Modifier.padding(Dimens.medium)
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    ) {
        Crossfade(state, modifier = Modifier.padding(top = it.calculateTopPadding())) { details ->
            when (details) {
                is UIState.Success -> UIContent(details.data)
                is UIState.Loading -> LoadingWidget()
                is UIState.Error -> ErrorContent(details.message)
                else -> Unit
            }
        }
    }
}

@Composable
fun UIContent(data: CoinDetails) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = Dimens.extraLarge)
    ) {
        AsyncImage(
            model = data.image?.large,
            contentDescription = "Details Image",
            placeholder = painterResource(id = com.coinscope.design.R.drawable.placeholder),
            error = painterResource(id = com.coinscope.design.R.drawable.placeholder),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(Dimens.iconLargest)
                .clip(Shapes.medium)
        )
        SpacerWidget(Dimens.small)
        Text(text = data.name.orEmpty(), style = MaterialTheme.typography.titleMedium)
        Text(text = data.symbol.orEmpty(), style = MaterialTheme.typography.bodySmall)
        SpacerWidget(Dimens.large)
        Text(text = "Current Price", style = MaterialTheme.typography.labelSmall)
        Text(
            text = data.marketData?.price.formatCurrency(),
            style = MaterialTheme.typography.titleSmall
        )
        SpacerWidget(Dimens.large)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Low 24H", style = MaterialTheme.typography.labelSmall)
                Text(
                    text = data.marketData?.low24h.formatCurrency(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "High 24H", style = MaterialTheme.typography.labelSmall)
                Text(
                    text = data.marketData?.high24h.formatCurrency(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
        SpacerWidget(Dimens.big)
        Text(
            text = "Description",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.align(Alignment.Start)
        )
        SpacerWidget(Dimens.small)
        Text(
            text = data.description.orEmpty(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.Start)
        )
        SpacerWidget(Dimens.big)
        Text(
            text = "Details",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.align(Alignment.Start)
        )
        data.genesisDate?.let {
            DetailsItem(
                key = "Genesis Date",
                value = it.formatDate()
            )
        }
        data.hashingAlgorithm?.let {
            DetailsItem(
                key = "Hashing Algorithm",
                value = it
            )
        }

        DetailsItem(
            key = "Market Cap Rank",
            value = "${data.rank}º"
        )

        data.links?.homepage?.let { homepage ->
            if (homepage.isNotEmpty()) {
                DetailsItem(
                    key = "Home Page",
                    value = homepage.first(),
                    isLink = true
                )
            }
        }

        data.links?.officialForumUrl?.let { officialForumUrl ->
            if (officialForumUrl.isNotEmpty()) {
                DetailsItem(
                    key = "Official Forum",
                    value = officialForumUrl.first(),
                    isLink = true
                )
            }
        }
    }
}

@Composable
fun DetailsItem(key: String, value: String, isLink: Boolean = false) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(detailsItemHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = key,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1F)
        )
        SpacerWidget(Dimens.large)
        if (isLink) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { context.openBrowser(value) }
            )
        } else {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
    HorizontalDivider()
}

@Preview(showBackground = true)
@Composable
private fun CoinDetailsPreview() {
    UIContent(
        data = CoinDetails(
            id = "bitcoin",
            symbol = "BTC",
            name = "Bitcoin",
            hashingAlgorithm = "SHA-256",
            categories = listOf("Cryptocurrency", "Store of Value"),
            description = "Bitcoin is a decentralized digital currency, without a central bank or single administrator.",
            links = Link(
                homepage = listOf("https://bitcoin.org"),
                officialForumUrl = listOf("https://bitcointalk.org"),
                reposUrl = ReposUrl(
                    github = listOf("https://github.com/bitcoin/bitcoin"),
                    bitbucket = null
                )
            ),
            image = Image(
                large = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png",
                small = "https://assets.coingecko.com/coins/images/1/small/bitcoin.png",
                thumb = "https://assets.coingecko.com/coins/images/1/thumb/bitcoin.png"
            ),
            genesisDate = "2009-01-03",
            rank = 1,
            marketData = MarketData(
                price = 50000.0,
                high24h = 51000.0,
                low24h = 49000.0,
                priceChange24h = -500.0
            )
        )
    )
}
