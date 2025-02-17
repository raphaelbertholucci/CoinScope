package com.coinscope.core.extensions

import com.coinscope.core.UIState
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<UIState<T>>.showLoading() {
    value = UIState.Loading
}

fun <T> MutableStateFlow<UIState<T>>.hideLoading() {
    value = UIState.Idle
}