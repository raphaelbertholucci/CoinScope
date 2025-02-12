package com.coinscope.ui

import androidx.compose.runtime.Composable
import org.koin.compose.KoinApplication

@Composable
fun ScreenPreview(
    screen: @Composable () -> Unit
) {
    KoinApplication(application = {
        modules(com.coinscope.modules)
    }) { screen() }
}