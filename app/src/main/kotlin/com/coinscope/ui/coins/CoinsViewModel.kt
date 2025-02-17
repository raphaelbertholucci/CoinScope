package com.coinscope.ui.coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coinscope.core.UIState
import com.coinscope.domain.interactor.GetCoins
import com.coinscope.domain.model.Coin
import com.coinscope.extensions.wrapResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class CoinsViewModel(private val getCoins: GetCoins) : ViewModel() {

    private val _content = MutableStateFlow<UIState<List<Coin>>>(UIState.Loading)
    val content = _content.asStateFlow()

    init {
        fetchCoins()
    }

    private fun fetchCoins() {
        getCoins(Unit)
            .map { _content.wrapResponse(it) }
            .catch { _content.value = UIState.Error() }
            .launchIn(viewModelScope)
    }
}