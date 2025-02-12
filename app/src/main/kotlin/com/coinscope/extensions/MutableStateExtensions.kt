package com.coinscope.extensions

import com.coinscope.core.UIState
import com.coinscope.domain.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<UIState<T>>.wrapResponse(resultWrapper: ResultWrapper<T>) {
    when (resultWrapper) {
        is ResultWrapper.Success -> {
            value = UIState.Success(data = resultWrapper.value)
        }

        is ResultWrapper.Error -> {
            UIState.Success(
                UIState.Error(
                    message = resultWrapper.error?.message
                        ?: "Something went wrong. Please try again later."
                )
            )
        }

        is ResultWrapper.NetworkError -> {
            UIState.Success(UIState.Error(message = "Unable to load data. Please, check your internet connection and try again."))
        }

        else -> Unit
    }
}
