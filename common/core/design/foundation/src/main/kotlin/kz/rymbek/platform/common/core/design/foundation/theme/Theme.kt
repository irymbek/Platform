package kz.rymbek.platform.common.core.design.foundation.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kz.rymbek.platform.common.business.model.ui.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.ui.enums.design.ModeConfig
import kz.rymbek.platform.common.core.design.foundation.theme.background.BackgroundTheme
import kz.rymbek.platform.common.core.design.foundation.theme.background.GradientColors
import kz.rymbek.platform.common.core.design.foundation.theme.background.LocalBackgroundTheme
import kz.rymbek.platform.common.core.design.foundation.theme.background.LocalGradientColors
import kz.rymbek.platform.common.core.design.foundation.theme.color.DefaultColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.DynamicColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.GreenAppleColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.LavenderColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.MidnightDuskColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.StrawberryColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.TakoColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.TealTurqoiseColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.TidalWaveColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.YinYangColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.YotsubaColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.BaseColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.custom.CustomColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.custom.LocalColorsPaletteCustom
import kz.rymbek.platform.common.core.design.foundation.theme.tint.LocalTintTheme
import kz.rymbek.platform.common.core.design.foundation.theme.tint.TintTheme

@Composable
fun AppTheme(
    modeConfig: ModeConfig = ModeConfig.FOLLOW_SYSTEM,
    appThemeBrand: AppThemeBrand = AppThemeBrand.DEFAULT,
    isDynamicColor: Boolean = false, // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val isDarkTheme = when (modeConfig) {
        ModeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
        ModeConfig.LIGHT -> false
        ModeConfig.DARK -> true
    }

    val colorScheme = getThemeColorScheme(
        appThemeBrand = appThemeBrand,
        isDarkTheme = isDarkTheme,
    )

    val gradientColors = GradientColors(
        top = colorScheme.inverseOnSurface,
        bottom = colorScheme.primaryContainer,
        container = colorScheme.surface,
    )

    val backgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp,
    )

    val tintTheme = when {
        isDynamicColor && supportsDynamicTheming() -> TintTheme(colorScheme.primary)
        else -> TintTheme()
    }

    val colorsPaletteCustom =
        if(isDarkTheme) CustomColorScheme.darkScheme
        else CustomColorScheme.lightScheme

    CompositionLocalProvider(
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
        LocalColorsPaletteCustom provides colorsPaletteCustom,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

@Composable
@ReadOnlyComposable
private fun getThemeColorScheme(
    appThemeBrand: AppThemeBrand,
    isDarkTheme: Boolean,
): ColorScheme {
    val colorScheme = if (appThemeBrand == AppThemeBrand.DYNAMIC) {
        DynamicColorScheme(LocalContext.current)
    } else {
        colorSchemes.getOrDefault(appThemeBrand, DefaultColorScheme)
    }
    return colorScheme.getColorScheme(
        isDark = isDarkTheme,
    )
}

private val colorSchemes: Map<AppThemeBrand, BaseColorScheme> = mapOf(
    AppThemeBrand.DEFAULT to DefaultColorScheme,
    AppThemeBrand.GREEN_APPLE to GreenAppleColorScheme,
    AppThemeBrand.LAVENDER to LavenderColorScheme,
    AppThemeBrand.MIDNIGHT_DUSK to MidnightDuskColorScheme,
    AppThemeBrand.STRAWBERRY_DAIQUIRI to StrawberryColorScheme,
    AppThemeBrand.TAKO to TakoColorScheme,
    AppThemeBrand.TEAL_TURQUOISE to TealTurqoiseColorScheme,
    AppThemeBrand.TIDAL_WAVE to TidalWaveColorScheme,
    AppThemeBrand.YIN_YANG to YinYangColorScheme,
    AppThemeBrand.YOTSUBA to YotsubaColorScheme,
)

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S