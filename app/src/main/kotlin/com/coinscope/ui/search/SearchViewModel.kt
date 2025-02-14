package com.coinscope.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coinscope.core.UIState
import com.coinscope.domain.interactor.SearchQuery
import com.coinscope.domain.model.SearchItem
import com.coinscope.extensions.wrapResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchViewModel(private val searchQuery: SearchQuery) : ViewModel() {

    val query = mutableStateOf("")

    val content = MutableStateFlow<UIState<List<SearchItem>>>(UIState.Idle)

    fun search() {
        searchQuery(query.value)
            .onEach { content.wrapResponse(it) }
            .catch { content.value = UIState.Error() }
            .launchIn(viewModelScope)
    }
}