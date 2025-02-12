package com.coinscope.domain

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val code: Int? = null, val error: Throwable? = null) : ResultWrapper<Nothing>()
    data object NetworkError : ResultWrapper<Nothing>()
}
