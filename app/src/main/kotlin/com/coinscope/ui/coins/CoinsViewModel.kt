package com.coinscope.ui.coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.coinscope.domain.interactor.GetCoins

class CoinsViewModel(getCoins: GetCoins) : ViewModel() {

    val pagedContent = getCoins(Unit).cachedIn(viewModelScope)


}