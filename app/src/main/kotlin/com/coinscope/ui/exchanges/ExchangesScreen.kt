package com.coinscope.ui.exchanges

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.coinscope.R
import com.coinscope.core.helpers.Helpers
import com.coinscope.design.resources.Dimens
import com.coinscope.design.widget.ErrorContent
import com.coinscope.design.widget.LoadingWidget
import com.coinscope.design.widget.SpacerWidget
import com.coinscope.domain.model.Exchange
import org.koin.androidx.compose.getViewModel

@Composable
fun ExchangesScreen(viewModel: ExchangesViewModel = getViewModel()) {
    val pagedContent = viewModel.pagedContent.collectAsLazyPagingItems()
    Scaffold(topBar = {
        Text(
            text = "Exchanges",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(all = Dimens.large)
        )
    }) {
        Crossfade(
            targetState = pagedContent,
            label = "Content",
            modifier = Modifier.padding(top = it.calculateTopPadding())
        ) { state ->
            when (state.loadState.refresh) {
                is LoadState.NotLoading -> Content(pagedContent)
                is LoadState.Loading -> LoadingWidget()
                is LoadState.Error -> ErrorContent(message = stringResource(R.string.generic_error_message))
                else -> Unit
            }
        }
    }
}

@Composable
fun Content(data: LazyPagingItems<Exchange>) {
    LazyColumn(
        contentPadding = PaddingValues(all = Dimens.paddingMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.large)
    ) {
        items(data.itemCount) { index ->
            val item = data[index]
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = item?.image,
                    contentDescription = item?.name,
                    placeholder = painterResource(id = com.coinscope.design.R.drawable.placeholder),
                    error = painterResource(id = com.coinscope.design.R.drawable.placeholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .shadow(elevation = Dimens.small)
                        .clip(MaterialTheme.shapes.small)
                )
                SpacerWidget(Dimens.large)
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item?.name.orEmpty(),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Helpers.safeLet(item?.trustScore, item?.yearEstablished) { score, year ->
                        Text(
                            text = "$year • Score: $score",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                item?.trustScoreRank?.let { rank ->
                    Text(
                        text = "${rank}º",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}
