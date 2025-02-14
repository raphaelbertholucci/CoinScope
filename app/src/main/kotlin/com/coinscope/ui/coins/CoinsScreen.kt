package com.coinscope.ui.coins

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.coinscope.core.UIState
import com.coinscope.core.extensions.formatCurrency
import com.coinscope.core.extensions.formatPercentage
import com.coinscope.design.resources.Dimens
import com.coinscope.design.resources.Shapes
import com.coinscope.design.widget.ErrorContent
import com.coinscope.design.widget.LoadingWidget
import com.coinscope.design.widget.SpacerWidget
import com.coinscope.domain.model.Coin
import com.coinscope.ui.ScreenPreview
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CoinsScreen(viewModel: CoinsViewModel = getViewModel()) {

    val contentState by viewModel.content.collectAsStateWithLifecycle()

    Scaffold {
        Crossfade(targetState = contentState) { content ->
            when (content) {
                is UIState.Success -> Content(content.data)
                is UIState.Error -> ErrorContent(content.message)
                is UIState.Loading -> LoadingWidget()
                else -> Unit
            }
        }
    }
}

@Composable
fun Content(coinList: List<Coin>) {
    LazyColumn(
        contentPadding = PaddingValues(all = Dimens.paddingMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.medium)
    ) {
        item {
            Text("Welcome Investor!", style = MaterialTheme.typography.headlineMedium)
        }
        items(coinList.size) { index ->
            val coin = coinList[index]
            CoinItem(coin)
        }
    }
}

@Composable
fun CoinItem(coin: Coin) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = Dimens.medium)
    ) {
        AsyncImage(
            model = coin.image,
            contentDescription = coin.name,
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
                text = coin.name.orEmpty(), style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = coin.symbol?.uppercase().orEmpty(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        val priceChangePercentage24h = coin.priceChangePercentage24h ?: 0.0
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
            text = coin.currentPrice.formatCurrency(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.widthIn(min = 70.dp),
            textAlign = TextAlign.End
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CoinsPreview() {
    ScreenPreview { CoinsScreen() }
}
