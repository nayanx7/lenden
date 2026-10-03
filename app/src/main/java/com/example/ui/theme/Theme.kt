package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = LendenEmerald,
    onPrimary = Color.Black,
    primaryContainer = LendenDarkTeal,
    onPrimaryContainer = LendenMint,
    secondary = LendenMint,
    onSecondary = Color.Black,
    secondaryContainer = LendenDarkSurfaceElevated,
    onSecondaryContainer = LendenTextWhite,
    tertiary = LendenBlue,
    onTertiary = Color.White,
    background = LendenOnyx,
    onBackground = LendenTextWhite,
    surface = LendenDarkSurface,
    onSurface = LendenTextWhite,
    surfaceVariant = LendenDarkSurfaceElevated,
    onSurfaceVariant = LendenTextMutedDark,
    outline = LendenDarkBorder,
    outlineVariant = LendenDarkBorderSubtle,
    error = LendenRose,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = LendenEmerald,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD7F8EE),
    onPrimaryContainer = Color(0xFF003828),
    secondary = Color(0xFF0F172A),
    onSecondary = Color.White,
    secondaryContainer = LendenLightSurfaceElevated,
    onSecondaryContainer = Color(0xFF0F172A),
    tertiary = LendenBlue,
    onTertiary = Color.White,
    background = LendenLightBg,
    onBackground = LendenTextPrimaryLight,
    surface = LendenLightSurface,
    onSurface = LendenTextPrimaryLight,
    surfaceVariant = LendenLightSurfaceElevated,
    onSurfaceVariant = LendenTextMutedLight,
    outline = LendenLightBorder,
    outlineVariant = LendenLightBorderSubtle,
    error = LendenRose,
    onError = Color.White
)

@Composable
fun LendenTheme(
    darkTheme: Boolean = true, // Default to sleek premium flagship dark mode
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = Color.Transparent.toArgb()
                window.navigationBarColor = Color.Transparent.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
