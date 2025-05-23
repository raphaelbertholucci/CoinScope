package com.coinscope.ui.search

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.coinscope.R
import com.coinscope.core.UIState
import com.coinscope.design.resources.Dimens
import com.coinscope.design.resources.Shapes
import com.coinscope.design.widget.ErrorContent
import com.coinscope.design.widget.LoadingWidget
import com.coinscope.design.widget.SearchWidget
import com.coinscope.design.widget.SpacerWidget
import com.coinscope.domain.model.SearchItem
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun SearchScreen(viewModel: SearchViewModel = koinViewModel(), onSelect: (String?) -> Unit) {
    val contentState by viewModel.content.collectAsStateWithLifecycle()
    val searchQuery = viewModel.query

    Scaffold(
        topBar = {
            Text(
                text = "Search",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = Dimens.large)
            )
        }
    ) {
        Column(Modifier.padding(top = it.calculateTopPadding())) {
            SearchWidget(
                text = searchQuery,
                modifier = Modifier.padding(all = Dimens.large)
            ) { query -> viewModel.updateSearchQuery(query) }

            Crossfade(targetState = contentState) { content ->
                when (content) {
                    is UIState.Success -> SearchContent(content.data, onSelect)
                    is UIState.Error -> ErrorContent(content.message)
                    is UIState.Loading -> LoadingWidget()
                    else -> HintContent()
                }
            }
        }
    }
}

@Composable
fun HintContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimens.extraLarge)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_manage_search),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
        )
        SpacerWidget(size = Dimens.large)
        Text(
            text = stringResource(R.string.search_idle_title),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        SpacerWidget(size = Dimens.small)
        Text(
            text = stringResource(R.string.search_idle_body),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SearchContent(data: List<SearchItem>, onSelect: (String?) -> Unit) {
    val groupedSearch = data.groupBy { it.type }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(Dimens.medium)) {
        groupedSearch.forEach { search ->
            stickyHeader {
                Text(
                    text = search.key.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surface)
                        .padding(horizontal = Dimens.large, vertical = Dimens.medium)
                )
            }
            items(
                count = data.size,
                key = { index -> data[index].id ?: index }) { index ->
                SearchItemContent(data[index], onSelect)
            }
        }
    }
}

@Composable
fun SearchItemContent(item: SearchItem, onSelect: (String?) -> Unit) {
    val onClick by rememberUpdatedState(onSelect)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = Dimens.medium)
            .clickable { if (item.type == "coin") onClick(item.id) }
    ) {
        item.thumb?.let { thumb ->
            AsyncImage(
                model = thumb,
                contentDescription = "Search Item Image",
                placeholder = painterResource(id = com.coinscope.design.R.drawable.placeholder),
                error = painterResource(id = com.coinscope.design.R.drawable.placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(Dimens.iconBig)
                    .clip(Shapes.medium)
            )
            SpacerWidget(Dimens.large)
        }
        Column(Modifier.weight(1f)) {
            Text(
                text = item.name.orEmpty(),
                style = MaterialTheme.typography.bodyMedium
            )
            item.symbol?.let { symbol ->
                Text(
                    text = symbol.uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
        SpacerWidget(Dimens.big)
        item.marketCapRank?.let { rank ->
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Market Cap",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "${rank}º",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        item.marketType?.let { marketType ->
            Text(
                text = marketType.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
