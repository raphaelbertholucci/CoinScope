package com.coinscope.design.widget

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacerWidget(size: Dp) {
    Spacer(modifier = Modifier.size(size))
}