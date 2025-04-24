package com.coinscope.ui.coins

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.coinscope.R
import com.coinscope.core.extensions.formatCurrency
import com.coinscope.core.extensions.formatPercentage
import com.coinscope.design.resources.Dimens
import com.coinscope.design.resources.Shapes
import com.coinscope.design.widget.ErrorContent
import com.coinscope.design.widget.LoadingWidget
import com.coinscope.design.widget.SpacerWidget
import com.coinscope.domain.model.Coin
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoinsScreen(viewModel: CoinsViewModel = koinViewModel(), onSelect: (Coin) -> Unit) {

    val contentState = viewModel.pagedContent.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            Text(
                text = "Welcome Investor!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(all = Dimens.large)
            )
        }
    ) {
        Crossfade(
            targetState = contentState,
            Modifier.padding(top = it.calculateTopPadding())
        ) { state ->
            when (state.loadState.refresh) {
                is LoadState.NotLoading -> Content(
                    coinList = state,
                    onSelect = onSelect,
                    isRefreshing = viewModel.isRefreshing.value,
                ) {
                    viewModel.refreshCoins()
                }

                is LoadState.Loading -> LoadingWidget()
                is LoadState.Error -> ErrorContent(message = stringResource(R.string.generic_error_message))
                else -> Unit
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
    coinList: LazyPagingItems<Coin>,
    onSelect: (Coin) -> Unit,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ) {
        LazyColumn(
            contentPadding = PaddingValues(all = Dimens.paddingMedium),
            verticalArrangement = Arrangement.spacedBy(Dimens.medium)
        ) {
            items(coinList.itemCount) { index ->
                val coin = coinList[index]
                CoinItem(coin) {
                    coin?.let(onSelect)
                }
            }
        }
    }
}

@Composable
fun CoinItem(coin: Coin?, onSelect: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = Dimens.medium)
            .clickable { onSelect() }
    ) {
        AsyncImage(
            model = coin?.image,
            contentDescription = coin?.name,
            placeholder = painterResource(id = com.coinscope.design.R.drawable.placeholder),
            error = painterResource(id = com.coinscope.design.R.drawable.placeholder),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(Dimens.iconBig)
                .clip(Shapes.medium)
        )
        SpacerWidget(Dimens.large)
        Column(Modifier.weight(1f)) {
            Text(
                text = coin?.name.orEmpty(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = coin?.symbol?.uppercase().orEmpty(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        val priceChangePercentage24h = coin?.priceChangePercentage24h ?: 0.0
        var pricePercentageColor = MaterialTheme.colorScheme.error
        var iconModifier = Modifier.size(Dimens.iconMedium)

        if (priceChangePercentage24h > 0.0) {
            pricePercentageColor = MaterialTheme.colorScheme.tertiary
            iconModifier = iconModifier.rotate(180F)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = priceChangePercentage24h.formatPercentage(),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End,
                color = pricePercentageColor
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = pricePercentageColor,
                modifier = iconModifier
            )
        }
        SpacerWidget(Dimens.big)
        Text(
            text = coin?.currentPrice.formatCurrency(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.widthIn(min = 70.dp),
            textAlign = TextAlign.End
        )
    }
}
