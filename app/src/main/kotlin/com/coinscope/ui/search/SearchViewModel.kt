package com.coinscope.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coinscope.core.UIState
import com.coinscope.core.extensions.hideLoading
import com.coinscope.core.extensions.showLoading
import com.coinscope.domain.interactor.SearchQuery
import com.coinscope.domain.model.SearchItem
import com.coinscope.extensions.wrapResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class SearchViewModel(private val searchQuery: SearchQuery) : ViewModel() {

    val query = mutableStateOf("")
    private val _searchQuery = MutableStateFlow("")

    private val _content = MutableStateFlow<UIState<List<SearchItem>>>(UIState.Idle)
    val content = _content.asStateFlow()

    init {
        observeSearchQuery()
    }

    fun updateSearchQuery(query: String) {
        if (query.isEmpty()) _content.hideLoading()
        _searchQuery.value = query
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun observeSearchQuery() {
        _searchQuery
            .debounce(1500)
            .distinctUntilChanged()
            .filter { it.length > 2 }
            .flatMapLatest { query -> searchQuery(query).onStart { _content.showLoading() } }
            .onEach { _content.wrapResponse(it) }
            .launchIn(viewModelScope)
    }
}