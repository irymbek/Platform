package kz.rymbek.platform.common.core.design.foundation.theme.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.BaseColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.errorContainerDark
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.errorContainerLight
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.errorDark
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.errorLight
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.onErrorContainerDark
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.onErrorContainerLight
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.onErrorDark
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.onErrorLight

internal object DefaultColorScheme : BaseColorScheme() {
    override val darkScheme: ColorScheme = darkColorScheme(
        primary = Color(0xFFC1C1FF),
        onPrimary = Color(0xFF2A2A60),
        primaryContainer = Color(0xFF414178),
        onPrimaryContainer = Color(0xFFE2DFFF),
        secondary = Color(0xFFC6C4DD),
        onSecondary = Color(0xFF2F2F42),
        secondaryContainer = Color(0xFF454559),
        onSecondaryContainer = Color(0xFFE2E0F9),
        tertiary = Color(0xFFE9B9D3),
        onTertiary = Color(0xFF46263A),
        tertiaryContainer = Color(0xFF5F3C51),
        onTertiaryContainer = Color(0xFFFFD8EB),
        error = errorDark,
        onError = onErrorDark,
        errorContainer = errorContainerDark,
        onErrorContainer = onErrorContainerDark,
        background = Color(0xFF131318),
        onBackground = Color(0xFFE4E1E9),
        surface = Color(0xFF131318),
        onSurface = Color(0xFFE4E1E9),
        surfaceVariant = Color(0xFF47464F),
        onSurfaceVariant = Color(0xFFC8C5D0),
        outline = Color(0xFF918F9A),
        outlineVariant = Color(0xFF47464F),
        scrim = Color(0xFF000000),
        inverseSurface = Color(0xFFE4E1E9),
        inverseOnSurface = Color(0xFF303036),
        inversePrimary = Color(0xFF585992),
        surfaceDim = Color(0xFF131318),
        surfaceBright = Color(0xFF39383F),
        surfaceContainerLowest = Color(0xFF0E0E13),
        surfaceContainerLow = Color(0xFF1B1B21),
        surfaceContainer = Color(0xFF1F1F25),
        surfaceContainerHigh = Color(0xFF2A292F),
        surfaceContainerHighest = Color(0xFF35343A),
    )

    override val lightScheme: ColorScheme = lightColorScheme(
        primary = Color(0xFF585992),
        onPrimary = Color(0xFFFFFFFF),
        primaryContainer = Color(0xFFE2DFFF),
        onPrimaryContainer = Color(0xFF14134A),
        secondary = Color(0xFF5D5C72),
        onSecondary = Color(0xFFFFFFFF),
        secondaryContainer = Color(0xFFE2E0F9),
        onSecondaryContainer = Color(0xFF1A1A2C),
        tertiary = Color(0xFF795369),
        onTertiary = Color(0xFFFFFFFF),
        tertiaryContainer = Color(0xFFFFD8EB),
        onTertiaryContainer = Color(0xFF2F1124),
        error = errorLight,
        onError = onErrorLight,
        errorContainer = errorContainerLight,
        onErrorContainer = onErrorContainerLight,
        background = Color(0xFFFCF8FF),
        onBackground = Color(0xFF1B1B21),
        surface = Color(0xFFFCF8FF),
        onSurface = Color(0xFF1B1B21),
        surfaceVariant = Color(0xFFE4E1EC),
        onSurfaceVariant = Color(0xFF47464F),
        outline = Color(0xFF777680),
        outlineVariant = Color(0xFFC8C5D0),
        scrim = Color(0xFF000000),
        inverseSurface = Color(0xFF303036),
        inverseOnSurface = Color(0xFFF3EFF7),
        inversePrimary = Color(0xFFC1C1FF),
        surfaceDim = Color(0xFFDCD9E0),
        surfaceBright = Color(0xFFFCF8FF),
        surfaceContainerLowest = Color(0xFFFFFFFF),
        surfaceContainerLow = Color(0xFFF6F2FA),
        surfaceContainer = Color(0xFFF0ECF4),
        surfaceContainerHigh = Color(0xFFEAE7EF),
        surfaceContainerHighest = Color(0xFFE4E1E9),
    )
}