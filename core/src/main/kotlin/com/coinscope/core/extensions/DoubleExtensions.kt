package com.coinscope.core.extensions

import java.text.NumberFormat
import java.util.Locale
import kotlin.math.absoluteValue

fun Double?.formatCurrency(): String {
    if (this == null) return "U$ 0,00"
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)
    return formatter.format(this)
}

fun Double?.formatPercentage(): String {
    if (this == null) return "0.0"
    return "%.2f".format(this.absoluteValue).plus("%")
}