package com.coinscope.core

sealed class UIState<out T> {
    data object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error(
        val message: String = "Something went wrong. Please try again later."
    ) : UIState<Nothing>()
}
