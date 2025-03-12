package com.coinscope.ui.coins

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.coinscope.domain.interactor.GetCoins
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CoinsViewModel(getCoins: GetCoins) : ViewModel() {

    private val _refresh = MutableSharedFlow<Unit>(replay = 1).apply { tryEmit(Unit) }
    val isRefreshing = mutableStateOf(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagedContent = _refresh
        .flatMapLatest {
            isRefreshing.value = true
            getCoins(Unit)
        }
        .onEach { isRefreshing.value = false }
        .distinctUntilChanged()
        .cachedIn(viewModelScope)

    fun refreshCoins() {
        viewModelScope.launch {
            _refresh.emit(Unit)
        }
    }
}