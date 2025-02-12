package com.coinscope.ui.coins

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coinscope.R
import com.coinscope.core.UIState
import com.coinscope.design.resources.Dimens
import com.coinscope.design.widget.SpacerWidget
import com.coinscope.domain.model.Coin
import com.coinscope.ui.ScreenPreview
import org.koin.androidx.compose.getViewModel

@Composable
fun CoinsScreen(viewModel: CoinsViewModel = getViewModel()) {

    val contentState by viewModel.content.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Crossfade(
            targetState = contentState,
            modifier = Modifier.padding(paddingValues)
        ) { content ->
            when (content) {
                is UIState.Success -> Content(content.data)
                is UIState.Error -> ErrorContent(content.message)
                is UIState.Loading -> LoadingWidget()
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
        modifier = Modifier.padding(all = Dimens.medium)
    ) {

    }
}

@Composable
fun ErrorContent(message: String) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(R.drawable.ic_error),
            contentDescription = "Error",
            tint = MaterialTheme.colorScheme.error
        )
        SpacerWidget(size = Dimens.medium)
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun LoadingWidget() {
    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(size = Dimens.iconSize)
                .align(Alignment.Center),
            strokeWidth = Dimens.defaultThickness
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CoinsPreview() {
    ScreenPreview { CoinsScreen() }
}
