package com.coinscope.core.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

fun Context.openBrowser(url: String) {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
        )
    } catch (e: Exception) {
        Log.e("Context.openBrowser", e.message, e)
    }
}