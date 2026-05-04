package com.example.fitapp.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Orange500,
    onPrimary = White,
    primaryContainer = Orange100,
    onPrimaryContainer = Color(0xFF3D1500),
    secondary = Teal700,
    onSecondary = White,
    secondaryContainer = Teal300.copy(alpha = 0.3f),
    onSecondaryContainer = Color(0xFF001F29),
    tertiary = Lime400,
    onTertiary = Color(0xFF1A3300),
    tertiaryContainer = Lime200.copy(alpha = 0.3f),
    onTertiaryContainer = Color(0xFF0D1A00),
    error = ErrorRed,
    onError = White,
    errorContainer = ErrorRed.copy(alpha = 0.1f),
    background = Gray50,
    onBackground = Gray900,
    surface = White,
    onSurface = Gray900,
    surfaceVariant = Gray100,
    onSurfaceVariant = Gray600,
    outline = Gray300,
    outlineVariant = Gray200
)

private val DarkColorScheme = darkColorScheme(
    primary = Orange400,
    onPrimary = Color(0xFF3D1500),
    primaryContainer = Orange500.copy(alpha = 0.2f),
    onPrimaryContainer = Orange200,
    secondary = Teal400,
    onSecondary = Color(0xFF001F29),
    secondaryContainer = Teal500.copy(alpha = 0.2f),
    onSecondaryContainer = Teal300,
    tertiary = Lime300,
    onTertiary = Color(0xFF0D1A00),
    tertiaryContainer = Lime400.copy(alpha = 0.2f),
    onTertiaryContainer = Lime200,
    error = ErrorRed,
    onError = White,
    errorContainer = ErrorRed.copy(alpha = 0.2f),
    background = DarkBackground,
    onBackground = Gray200,
    surface = DarkSurface,
    onSurface = Gray200,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = Gray500,
    outline = Gray600,
    outlineVariant = Gray700
)

private val OledDarkColorScheme = darkColorScheme(
    primary = Orange400,
    onPrimary = Color(0xFF3D1500),
    primaryContainer = Orange500.copy(alpha = 0.15f),
    onPrimaryContainer = Orange200,
    secondary = Teal400,
    onSecondary = Color(0xFF001F29),
    secondaryContainer = Teal500.copy(alpha = 0.15f),
    onSecondaryContainer = Teal300,
    tertiary = Lime300,
    onTertiary = Color(0xFF0D1A00),
    tertiaryContainer = Lime400.copy(alpha = 0.15f),
    onTertiaryContainer = Lime200,
    error = ErrorRed,
    onError = White,
    errorContainer = ErrorRed.copy(alpha = 0.15f),
    background = OledBlack,
    onBackground = Gray300,
    surface = OledSurface,
    onSurface = Gray300,
    surfaceVariant = OledSurfaceVariant,
    onSurfaceVariant = Gray500,
    outline = Gray700,
    outlineVariant = Gray800
)

import androidx.compose.ui.graphics.Color

@Composable
fun FitAppTheme(
    themeMode: ThemeMode = ThemeMode.DARK,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeMode) {
        ThemeMode.LIGHT -> LightColorScheme
        ThemeMode.DARK -> DarkColorScheme
        ThemeMode.OLED_DARK -> OledDarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = FitnessTypography,
        shapes = FitnessShapes,
        content = content
    )
}
