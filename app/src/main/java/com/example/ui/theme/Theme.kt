package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4DB6AC),
    onPrimary = Color(0xFF003730),
    primaryContainer = Color(0xFF005047),
    onPrimaryContainer = Color(0xFFE0F2F1),
    secondary = Color(0xFFFFB74D),
    onSecondary = Color(0xFF4E2600),
    secondaryContainer = Color(0xFF6F3800),
    onSecondaryContainer = Color(0xFFFFE0B2),
    tertiary = Color(0xFF81D4FA),
    background = Color(0xFF121B22),
    surface = Color(0xFF1E293B),
    onBackground = Color(0xFFE2E8F0),
    onSurface = Color(0xFFE2E8F0),
    surfaceVariant = Color(0xFF334155),
    onSurfaceVariant = Color(0xFFCBD5E1)
)

private val LightColorScheme = lightColorScheme(
    primary = SaharaPrimary,
    onPrimary = SaharaOnPrimary,
    primaryContainer = SaharaPrimaryContainer,
    onPrimaryContainer = SaharaOnPrimaryContainer,
    secondary = SaharaSecondary,
    onSecondary = SaharaOnSecondary,
    secondaryContainer = SaharaSecondaryContainer,
    onSecondaryContainer = SaharaOnSecondaryContainer,
    tertiary = SaharaTertiary,
    onTertiary = SaharaOnTertiary,
    tertiaryContainer = SaharaTertiaryContainer,
    onTertiaryContainer = SaharaOnTertiaryContainer,
    background = SaharaBackground,
    surface = SaharaSurface,
    onBackground = SaharaOnBackground,
    onSurface = SaharaOnSurface,
    surfaceVariant = SaharaSurfaceVariant,
    onSurfaceVariant = SaharaOnSurfaceVariant
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

