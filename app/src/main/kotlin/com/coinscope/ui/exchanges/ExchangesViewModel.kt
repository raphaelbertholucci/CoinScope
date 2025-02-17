package com.coinscope.ui.exchanges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.coinscope.domain.interactor.GetExchanges

class ExchangesViewModel(getExchanges: GetExchanges) : ViewModel() {

    val pagedContent = getExchanges(Unit).cachedIn(viewModelScope)
}