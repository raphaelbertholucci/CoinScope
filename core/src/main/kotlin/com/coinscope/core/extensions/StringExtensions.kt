package com.coinscope.core.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val DEFAULT_DATE_PATTERN = "yyyy-MM-dd"
const val DATE_PATTERN = "dd MMM yyyy"

fun String.formatDate(
    fromPattern: String? = DEFAULT_DATE_PATTERN,
    toPattern: String = DATE_PATTERN
): String {
    return runCatching {
        val dateFormatted =
            SimpleDateFormat(fromPattern, Locale.getDefault()).parse(this) ?: Date()
        SimpleDateFormat(toPattern, Locale.getDefault()).format(dateFormatted)
    }.getOrDefault("")
}