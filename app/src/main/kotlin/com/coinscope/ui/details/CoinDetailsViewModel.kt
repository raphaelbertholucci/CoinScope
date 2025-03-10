package com.coinscope.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coinscope.core.UIState
import com.coinscope.domain.interactor.GetCoinDetailsByID
import com.coinscope.domain.model.CoinDetails
import com.coinscope.extensions.wrapResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class CoinDetailsViewModel(
    private val id: String,
    val getCoinDetailsByID: GetCoinDetailsByID
) : ViewModel() {

    private val _details = MutableStateFlow<UIState<CoinDetails>>(UIState.Loading)
    val details = _details.asStateFlow()

    init {
        getDetails()
    }

    private fun getDetails() {
        getCoinDetailsByID(id)
            .map { _details.wrapResponse(it) }
            .catch {
                _details.value = UIState.Error(
                    it.message ?: "Something went wrong. Please try again later."
                )
            }
            .launchIn(viewModelScope)
    }
}