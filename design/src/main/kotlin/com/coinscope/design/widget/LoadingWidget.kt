package com.coinscope.design.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.coinscope.design.resources.Dimens

@Composable
fun LoadingWidget(size: Dp = Dimens.iconBig) {
    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(size = size)
                .align(Alignment.Center),
            strokeWidth = Dimens.loadingThickness
        )
    }
}