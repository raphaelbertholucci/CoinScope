package com.coinscope.design.resources

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.coinscope.design.R

val Nunito = FontFamily(
    Font(R.font.nunito, FontWeight.Normal)
)
val PlayFair = FontFamily(
    Font(R.font.playfair_regular, FontWeight.Normal)
)

val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 20.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 22.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 24.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = PlayFair,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    )
)