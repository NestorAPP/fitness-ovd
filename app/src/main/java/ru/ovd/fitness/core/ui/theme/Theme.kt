package ru.ovd.fitness.core.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary              = OvdDarkBlue,
    onPrimary            = SurfaceWhite,
    primaryContainer     = OvdLightBlue,
    onPrimaryContainer   = OvdDarkBlue,

    secondary            = OvdBlue,
    onSecondary          = SurfaceWhite,

    tertiary             = TriRed,
    onTertiary           = SurfaceWhite,

    background           = BackgroundSoft,
    onBackground         = TextPrimary,

    surface              = SurfaceWhite,
    onSurface            = TextPrimary,
    surfaceVariant       = OvdLightBlue,
    onSurfaceVariant     = TextSecondary,

    error                = StatusRed,
    onError              = SurfaceWhite
)

@Composable
fun FitnessOVDTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)

            val controller = WindowCompat.getInsetsController(window, view)
            controller.isAppearanceLightStatusBars = true
            controller.isAppearanceLightNavigationBars = true
        }
    }

    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content
    )
}
