package com.coinscope.design.resources

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Blue,               // Keep Blue as the main theme color
    secondary = DarkGrey,         // Use DarkGrey for contrast in light mode
    tertiary = Green,             // Keep Green for highlights
    background = White,           // White background for light mode
    surface = LightGrey,  // Soft light grey surface to differentiate from background
    onPrimary = White,            // White text/icons on the Primary color (Blue)
    onSecondary = White,          // White text/icons on the Secondary color (DarkGrey)
    onBackground = DarkGrey,      // Dark text/icons on White background
    onSurface = DarkGrey,         // Dark text/icons on Light surfaces
    error = Red,                  // Keep Red as the error color
    onError = White               // Ensure text on error backgrounds is readable
)

private val DarkColorScheme = darkColorScheme(
    primary = Blue,
    secondary = Grey,
    tertiary = Green,
    background = Background,
    surface = SecondaryBackground,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White,
    error = Red,
    onError = White
)

@Composable
fun CoinScopeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}